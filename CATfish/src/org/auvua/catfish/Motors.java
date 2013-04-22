package org.auvua.catfish;

import gnu.io.*;

public class Motors extends Arduino {

	public Motors(String port_name, int timeout, int baud_rate) {
		super(port_name, timeout, baud_rate);
	}

	@Override
	public void send(char[] data) {
	}

	public void update(MotionVector v) {
	}

	@Override
	public char[] received() {
		return null;
	}
}
