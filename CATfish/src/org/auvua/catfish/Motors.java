package org.auvua.catfish;

import java.util.Arrays;


public class Motors extends Arduino {

	public Motors(String port_name, int timeout, int baud_rate, CATFishModel model) {
		super(port_name, timeout, baud_rate, model);
	}

	@Override
	public void send(char[] data) {
		byte[] msg = new byte[21];
		msg[0] = 127;
		for(byte i = 0; i < data.length; i++) {
			msg[3*i+1] = i;
			msg[3*i+2] = (byte)(data[i] > 127 ? 1 : 0);
			msg[3*i+3] = (byte)(data[i] > 127 ? -data[i] : data[i]);
		}
		msg[19] = msg[20] = 0x00;
		System.out.println(Arrays.toString(msg));
		write(msg);
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
		
		float max_motor_speed = Math.max(Math.abs(fl), Math.max(Math.abs(fr), Math.max(Math.abs(rl), Math.abs(rr))));
		float motor_scaling = speed * (max_motor_speed == 0 ? 0 : 1.0f/max_motor_speed);
		
		speeds[0] = (char)(fl * motor_scaling);
		speeds[1] = (char)(fr * motor_scaling);
		speeds[2] = (char)(rl * motor_scaling);
		speeds[3] = (char)(rr * motor_scaling);
		speeds[4] = speeds[5] = (char)(vert * speed);
		
		send(speeds);
	}

	@Override
	public char[] received() {
		return null;
	}

	@Override
	public void backgroundTask() {
		update(model.motion);
	}
}
