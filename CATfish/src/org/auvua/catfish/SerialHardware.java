package org.auvua.catfish;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import gnu.io.*;

public abstract class SerialHardware implements SerialPortEventListener,
		Runnable {
	private SerialPort port;
	private BufferedReader input;
	private OutputStream output;
	private Thread worker;

	// 2000ms
	private int TIME_OUT = 2000;
	private int BAUD_RATE = 9600;
	private int databits;
	private int parity;
	private int stopbit;

	private String port_name;

	protected final static Logger LOGGER = Logger.getLogger(Arduino.class
			.getName());

	public SerialHardware(String port_name, int timeout, int baud_rate,
			int databits, int parity, int stopbit) {
		TIME_OUT = timeout;
		BAUD_RATE = baud_rate;
		this.port_name = port_name;
		this.databits = databits;
		this.parity = parity;
		this.stopbit = stopbit;
	}

	public void initalize() {
		String msg = null;
		CommPortIdentifier identifier = null;
		Enumeration<CommPortIdentifier> ports = CommPortIdentifier
				.getPortIdentifiers();

		while (ports.hasMoreElements()) {
			CommPortIdentifier cur_identifier = ports.nextElement();

			if (cur_identifier.getName() == port_name) {
				identifier = cur_identifier;
				break;
			}
		}

		if (identifier == null) {
			LOGGER.log(Level.SEVERE, "Unable to find the aurduino.");
			System.exit(1);
		}

		try {
			port = (SerialPort) identifier.open(this.getClass().getName(),
					TIME_OUT);

			// Configure serial port
			port.setSerialPortParams(BAUD_RATE, databits,
					stopbit, parity);

			// Configure input/output streams
			InputStreamReader input_stream;
			input_stream = new InputStreamReader(port.getInputStream());
			input = new BufferedReader(input_stream);
			output = port.getOutputStream();

			// Register listener
			port.addEventListener(this);
			port.notifyOnDataAvailable(true);
		} catch (Exception e) {
			msg = String.format("Unable to connect to arduino.\n %s",
					e.toString());
			LOGGER.log(Level.SEVERE, msg);
		}

		worker = new Thread(this);
		worker.start();
	}

	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public void close() {
		if (port != null) {
			port.removeEventListener();
			port.close();
		}
	}
}
