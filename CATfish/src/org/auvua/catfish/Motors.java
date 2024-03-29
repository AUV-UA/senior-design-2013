package org.auvua.catfish;

import java.io.IOException;
import java.util.Arrays;

public class Motors extends Arduino {

	private final int MOTOR_ID_FL = 2;
	private final int MOTOR_ID_FR = 4;
	private final int MOTOR_ID_RL = 1;
	private final int MOTOR_ID_RR = 3;
	private final int MOTOR_ID_VL = 0;
	private final int MOTOR_ID_VR = 5;
	
	private final float battery_scaling = 0.8923f;
	private final float depth_offset = -49.97f;
	private final float depth_scaling = 1.0f;
	
	private float battery;
	private float depth;

	public Motors(String port_name, int timeout, int baud_rate,
			CATFishModel model) {
		super(port_name, timeout, baud_rate, model);
		
		battery = depth = 0;
	}

	@Override
	public void send(char[] data) {
		byte[] msg = new byte[21];

		msg[0] = 127; // Ready byte of 127, for indexing.

		// Bytes sent per motor are 3: first is motor id, second is direction
		// bit (1 = backwards),
		// and third is speed between 0 and 100 percent.
		for (byte i = 0; i < data.length; i++) {
			msg[3 * i + 1] = i;
			msg[3 * i + 2] = (byte) (data[i] > 127 ? 1 : 0);
			msg[3 * i + 3] = (byte) (data[i] > 127 ? -data[i] : data[i]);
		}

		msg[19] = msg[20] = 0x00; // checksum bytes (not implemented yet)
		write(msg); // write the packet
	}

	public void update(MotionVector v) {
		char[] speeds = new char[6];

		float speed = v.getScaling();
		float x = v.getXComponent();
		float y = v.getYComponent();
		float z = v.getZComponent();
		float a = v.getAComponent();
		float fl, fr, rl, rr, vert;

		fl = y + x + a;
		fr = y - x - a;
		rl = -y + x - a;
		rr = -y - x + a;
		vert = (z > 1 ? 1 : (z < -1 ? -1 : z));

		float max_motor_speed = Math.max(Math.abs(fl),
				Math.max(Math.abs(fr), Math.max(Math.abs(rl), Math.abs(rr))));
		float motor_scaling = speed
				* (max_motor_speed == 0 ? 0 : 1.0f / max_motor_speed);

		speeds[MOTOR_ID_FL] = (char) (fl * motor_scaling);
		speeds[MOTOR_ID_FR] = (char) (fr * motor_scaling);
		speeds[MOTOR_ID_RL] = (char) (rl * motor_scaling);
		speeds[MOTOR_ID_RR] = (char) (rr * motor_scaling);
		speeds[MOTOR_ID_VL] = (char) (vert * speed);
		speeds[MOTOR_ID_VR] = (char) (vert * speed);

		send(speeds);
	}

	@Override
	public Object[] received() {
		int[] msg = new int[6];

		try {
			msg[0] = input.read();
			msg[1] = input.read();
			msg[2] = input.read();
			msg[3] = input.read();
			msg[4] = input.read();
			msg[5] = input.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int sensor = (msg[1] << 8) + msg[2];
		float depth_new = (((float) sensor * 5.0f / 1024.0f) + 0.204f) / 0.0204f;
		depth_new += depth_offset;
		depth_new *= depth_scaling;

		/* 819.2 = 4/5 of 24V with a resolution of 1024 */
		sensor = (msg[4] << 8) + msg[5];
		float battery_new = (float) (sensor * 5.0f / 1024.0f) * 6.0f;
		battery_new *= battery_scaling;
		
		battery = .9f * battery + .1f * battery_new;
		depth = .9f * depth + .1f * depth_new;

		Float[] result = new Float[2];
		result[0] = (Float) depth;
		result[1] = (Float) battery;
		return result;
	}

	@Override
	public void backgroundTask() {
		update(model.motion);
	}
}
