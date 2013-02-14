package org.auvua.catfish;

import gnu.io.*;

public class Arduino extends SerialHardware implements HardwareEventListener {

	public Arduino(String port_name, int timeout, int baud_rate) {
		super(port_name, timeout, baud_rate, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
	}

	@Override
	public void hardwareEvent(HardwareEvent event) {
	}
}
