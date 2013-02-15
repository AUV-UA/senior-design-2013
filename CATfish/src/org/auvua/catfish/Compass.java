package org.auvua.catfish;

import gnu.io.*;

public class Compass extends SerialHardware {

	public Compass(String port_name, int timeout, int baud_rate) {
		super(port_name, timeout, baud_rate, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
	}
}
