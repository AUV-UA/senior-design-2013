package org.auvua.catfish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import gnu.io.*;

public abstract class SerialHardware implements SerialPortEventListener,
		Runnable {
	private SerialPort port;
	private Thread worker;
	private ArrayList<HardwareEventListener> hardware_listeners;

	BufferedReader input;
	OutputStream output;

	// 2000ms
	private int TIME_OUT = 2000;
	private int BAUD_RATE = 9600;
	private int databits;
	private int parity;
	private int stopbit;

	private String port_name;

	protected final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public SerialHardware(String port_name, int timeout, int baud_rate,
			int databits, int parity, int stopbit) {
		TIME_OUT = timeout;
		BAUD_RATE = baud_rate;
		this.port_name = port_name;
		this.databits = databits;
		this.parity = parity;
		this.stopbit = stopbit;
		this.hardware_listeners = new ArrayList<HardwareEventListener>();
	}

	/**
	 * Opens the hardware to listen on the serial port on separate thread.
	 */
	public void initalize() {
		String msg = null;
		CommPortIdentifier identifier = null;
		
                @SuppressWarnings("rawtypes")
		Enumeration ports = (Enumeration) CommPortIdentifier
				.getPortIdentifiers();

		while (ports.hasMoreElements()) {
			CommPortIdentifier cur_identifier = (CommPortIdentifier) ports
					.nextElement();

			if (cur_identifier.getName().equals(port_name)) {
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
			port.setSerialPortParams(BAUD_RATE, databits, stopbit, parity);

			// Configure input/output streams
			InputStreamReader input_stream;
			input_stream = new InputStreamReader(port.getInputStream());
			input = new BufferedReader(input_stream);
			output = port.getOutputStream();

			// Register listener
			port.addEventListener(this);
			port.notifyOnDataAvailable(true);
		} catch (Exception e) {
			msg = String.format("Unable to connect to hardware on port %s.\n %s", port.getName(), e.toString());
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
	
	public byte[] read() {
		byte[] buffer = new byte[1024];
		byte next;
		
		try {
			for(int i = 0; (next = (byte)input.read()) >= 0; i++)
				buffer[i] = next; 
		} catch (IOException e) {
			String msg = String.format("Nothing to read.\n %s", e.toString());
			LOGGER.log(Level.WARNING, msg);
		}
		
		return buffer;
	}
	
	public void write(byte[] message) {
		try {
			output.write(message);
		} catch (IOException e) {
			String msg = String.format("Failed to write to port.\n %s", e.toString());
			LOGGER.log(Level.WARNING, msg);
		}
	}

	/**
	 * Closes the connection to the serial port.
	 */
	public void close() {
		if (port != null) {
			port.removeEventListener();
			port.close();
		}
	}

	// Event handling

	/**
	 * Handles a serial port event and passes it along to the hardware event
	 * listeners.
	 */
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
				String data = input.readLine();
				sendHardwareEvent(data);
			} catch (Exception e) {
				String msg = String.format(
						"Unable to read from input stream.\n %s", e.toString());
				LOGGER.log(Level.SEVERE, msg);
			}
			break;
		}
	}

	/**
	 * Adds a listener for hardware events being passed from the serial port.
	 * 
	 * @param listener
	 */
	public synchronized void addHardwareListener(HardwareEventListener listener) {
		this.hardware_listeners.add(listener);
	}

	/**
	 * Removes the listener on events from the serial port.
	 * 
	 * @param listener
	 */
	public synchronized void removeHardwareListener(
			HardwareEventListener listener) {
		hardware_listeners.remove(listener);
	}

	/**
	 * Generates hardware events for each listener
	 * 
	 * @param data
	 */
	private synchronized void sendHardwareEvent(String data) {
		HardwareEvent event = new HardwareEvent(this, data);

		for (HardwareEventListener listener : hardware_listeners) {
			listener.hardwareEvent(event);
		}
	}
}
