package org.auvua.catfish;

import java.util.logging.Level;

import gnu.io.*;


public class Arduino extends SerialHardware {

	public Arduino(String port_name, int timeout, int baud_rate) {
		super(port_name, timeout, baud_rate, SerialPort.DATABITS_8,
				SerialPort.PARITY_NONE, SerialPort.STOPBITS_1);
	}
}
