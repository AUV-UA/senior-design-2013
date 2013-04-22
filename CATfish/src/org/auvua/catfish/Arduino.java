package org.auvua.catfish;

import gnu.io.*;

public abstract class Arduino extends SerialHardware {
	public Arduino(String port_name, int timeout, int baud_rate) {
		super(port_name, timeout, baud_rate, SerialPort.DATABITS_8,
				SerialPort.PARITY_NONE, SerialPort.STOPBITS_1);
	}
}
