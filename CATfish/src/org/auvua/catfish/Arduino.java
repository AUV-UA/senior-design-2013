package org.auvua.catfish;

import java.util.logging.*;

import gnu.io.*;

public class Arduino extends SerialHardware {

	public Arduino(String port_name, int timeout, int baud_rate) {
		super(port_name, timeout, baud_rate, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
	}

	/**
	 * Handles a serial port event. The data is sent to listeners.
	 */
	@Override
	public synchronized void serialEvent(SerialPortEvent arg0) {
		switch (arg0.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			try {
				String data = input.readLine();
			} catch (Exception e) {
				String msg = String.format(
						"Unable to read from input stream.\n %s", e.toString());
				LOGGER.log(Level.SEVERE, msg);
			}
			break;
		}
	}
}
