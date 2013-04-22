package org.auvua.catfish;

import java.util.logging.Level;

import gnu.io.*;

/**
 * Handles communication between an Arduino sending custom formatted messages.
 * Messages to the Arduino should follow this format: **xxxxxxxxxx** where two
 * '*'s mark the start and end of a message and each consecutive 'x' represents
 * the digital state of the target digital outputs from 0 to 10. 'x' should be
 * 'h' to set the given output high and 'l' to set it low. Received messages are
 * formatted as follows: *xxxxabababababab- where '*' marks the beginning of a
 * message, '-' marks the end, 'x' represents one of four digital inputs, and
 * 'ab' is a two-byte unsigned representation of one of six 10-bit analog
 * inputs.
 * 
 * @author forbesk
 * @author erbriones
 * 
 *         TODO: adjust formatting of messages to match that of received
 *         messages.
 */
public class Arduino extends SerialHardware {

	private boolean[] digital_inputs;
	private boolean[] digital_outputs;

	private boolean[] analog_inputs;
	private boolean[] analog_outputs;

	public Arduino(String port_name, int timeout, int baud_rate) {
		super(port_name, timeout, baud_rate, SerialPort.DATABITS_8,
				SerialPort.PARITY_NONE, SerialPort.STOPBITS_1);

		digital_inputs = new boolean[5];
		digital_outputs = new boolean[10];

		analog_inputs = new boolean[5];
		analog_outputs = new boolean[4];

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

				// find beginning of message
				while ((char) input.read() != '*')
					;
				data[0] = '*';

				// read all bytes
				while (bytes_read < 17 && (c = (char) input.read()) >= 0) {
					bytes_read++;
					data[bytes_read] = c;
				}

				// clear the input buffer in case any stray messages are left
				// while(input.ready())
				// input.skip(1);

				if ((char) data[17] == '-')
					sendHardwareEvent(data);
			} catch (Exception e) {
				String msg = String.format(
						"Unable to read from input stream.\n %s", e.toString());
				LOGGER.log(Level.SEVERE, msg);
			}
			break;
		}
	}
}
