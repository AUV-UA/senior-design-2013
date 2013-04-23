package org.auvua.catfish;


public class Motors extends Arduino {

	public Motors(String port_name, int timeout, int baud_rate) {
		super(port_name, timeout, baud_rate);
	}

	@Override
	public void send(char[] data) {
		byte[] msg = new byte[14];
		for(int i = 0; i < data.length; i++) 
			msg[i] = (byte)data[i];
		msg[12] = msg[13] = 0x00;
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
		
		float max_motor_speed = Math.max(fl, Math.max(fr, Math.max(rl, rr)));
		float motor_scaling = speed * (max_motor_speed == 0 ? 0 : 1/max_motor_speed);
		speeds[0] = (char)(fl * motor_scaling + 100);
		speeds[1] = (char)(fr * motor_scaling + 100);
		speeds[2] = (char)(rl * motor_scaling + 100);
		speeds[3] = (char)(rr * motor_scaling + 100);
		speeds[4] = speeds[5] = (char)(vert * speed + 100);
		
		send(speeds);
	}

	@Override
	public char[] received() {
		return null;
	}
}
