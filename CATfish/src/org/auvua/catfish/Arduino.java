package org.auvua.catfish;

import java.util.logging.Level;

import gnu.io.*;

public class Arduino extends SerialHardware {

	public Arduino(String port_name, int timeout, int baud_rate) {
		super(port_name, timeout, baud_rate, SerialPort.DATABITS_8,
				SerialPort.PARITY_NONE, SerialPort.STOPBITS_1);
	}
	
	@Override
	public synchronized void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
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
				char[] data = new char[18];
				char c;
				int bytes_read = 0;
				
				//find beginning of message
				while((char)input.read() != '*');
				data[0] = '*';
				
				//read all bytes
				while(bytes_read < 17 && (c = (char)input.read()) >= 0) {
					bytes_read++;
					data[bytes_read] = c;
				}
				
				//clear the input buffer in case any stray messages are left
				//while(input.ready())
				//	input.skip(1);

				if((char)data[17] == '-') sendHardwareEvent(data);
			} catch (Exception e) {
				String msg = String.format(
						"Unable to read from input stream.\n %s", e.toString());
				LOGGER.log(Level.SEVERE, msg);
			}
			break;
		}
	}
}
