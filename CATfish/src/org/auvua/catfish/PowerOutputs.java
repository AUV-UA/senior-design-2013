package org.auvua.catfish;

import java.util.logging.Level;

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
public class PowerOutputs extends Arduino {

	private boolean[] digital_inputs;
	private boolean[] digital_outputs;

	private boolean[] analog_inputs;
	private boolean[] analog_outputs;
	
	public PowerOutputs(String port_name, int timeout, int baud_rate, CATFishModel model) {
		super(port_name, timeout, baud_rate, model);
		
		digital_inputs = new boolean[5];
		digital_outputs = new boolean[10];

		analog_inputs = new boolean[5];
		analog_outputs = new boolean[4];
		
	}
	
	public char[] received() {
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
				return data;
		} catch (Exception e) {
			String msg = String.format(
					"Unable to read from input stream.\n %s", e.toString());
			LOGGER.log(Level.SEVERE, msg);
		}
		return null;
	}

	@Override
	public void send(char[] data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backgroundTask() {
		byte msg[] = new byte[14];
		msg[0] = msg[1] = msg[12] = msg[13] = '*';
		for (int n = 0; n < 10; n++)
			msg[n + 2] = (byte) (model.pins_do[n] ? 'h' : 'l');

		write(msg);
	}
	
}
