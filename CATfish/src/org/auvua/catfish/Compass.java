package org.auvua.catfish;

import gnu.io.SerialPort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Compass extends SerialHardware {
	
	private float heading;
	private float pitch;
	private float roll;
	
	public Compass(String port_name, int timeout, int baud_rate) {
		super(port_name, timeout, baud_rate, SerialPort.DATABITS_8,
				SerialPort.PARITY_NONE, SerialPort.STOPBITS_1);
		
	}
	
	@Override
	public void initialize() {
		super.initialize();
		
		byte[] setDataComponents = {0x00, 0x0a, 0x05, 0x05, 0x18, 0x19, 0x4f, 0x09, 0x5c, 0x55};
		byte[] startContinuousMode = {0x00, 0x05, 0x15, (byte) 0xbd, 0x61};
		
		write(setDataComponents);
		write(startContinuousMode);
	}

	@Override
	public void send(char[] data) {
		
	}

	@Override
	public Object[] received() {
		int[] msg = new int[25];
		Long heading_raw = new Long(0);
		Long pitch_raw = new Long(0);
		Long roll_raw = new Long(0);
		
		try {
			int x = 0;

			InputStreamReader is = new InputStreamReader(input);
			while(!is.ready());
			msg[0] = input.read();
			x++;
			while(!is.ready());
			
			while(x < 25) {
				msg[x] = input.read();
				if(msg[0] == 0 && msg[1] == 25) {
					x++;
				} else {
					msg[0] = msg[1];
				}
			}

			for(int i = 3; i >= 0; i--)
				heading_raw += msg[i+5] << ((3-i)*8);
			for(int i = 3; i >= 0; i--)
				pitch_raw += msg[i+10] << ((3-i)*8);
			for(int i = 3; i >= 0; i--)
				roll_raw += msg[i+15] << ((3-i)*8);
			
			heading = Float.intBitsToFloat(heading_raw.intValue());
			pitch = Float.intBitsToFloat(pitch_raw.intValue());
			roll = Float.intBitsToFloat(roll_raw.intValue());
			
			Object[] result = {heading, pitch, roll};
			return result;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
}
