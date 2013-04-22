package org.auvua.catfish;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.auvua.catfish.CATFishPanel.Connections;

/**
 * Collects data from telemetry sources, writes values to motors, and interfaces
 * with the CATFishPanel to deliver information to user.
 * 
 * @author forbesk
 * @author erbriones
 */
public class CATFishModel implements HardwareEventListener,
		ControllerEventListener {

	/* Global Java logger reference */
	public static final Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private CATFishPanel panel;

	/* Current desired motion of the robot */
	MotionVector motion;

	/* Hardware */
	private HashMap<String, SerialHardware> hardware;
	private Timer scheduler;

	/* Arduino digital outputs */
	public boolean pins_do[];

	/* Arduino digital inputs */
	public boolean pins_di[];

	/* Arduino analog inputs */
	public int pins_ai[];

	/**
	 * Initializes data and collects initial list of available serial ports.
	 * 
	 * @param panel
	 *            Associated CATFishPanel object (view/controller of MVC
	 *            paradigm)
	 */
	public CATFishModel(CATFishPanel panel) {
		motion = new MotionVector();

		hardware = new HashMap<String, SerialHardware>();
		scheduler = new Timer();

		this.panel = panel;

		pins_do = new boolean[10];
		pins_di = new boolean[4];
		pins_ai = new int[6];

		// check for generic comm ports
		HashSet<CommPortIdentifier> ports = getAvailableSerialPorts();
		if (ports.size() > 0) {
			for (CommPortIdentifier port : ports) {
				LOGGER.info("USB port available: " + port.getName());
			}
			panel.setAvailableSerialPorts(ports);
		} else {
			LOGGER.info("No USB ports available.");
		}
	}

	/**
	 * Identifies and returns a hashset of all available Comm ports.
	 * 
	 * @return A HashSet containing the CommPortIdentifier for all serial ports
	 *         that are not currently being used.
	 */
	public HashSet<CommPortIdentifier> getAvailableSerialPorts() {
		HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
		Enumeration<?> thePorts = CommPortIdentifier.getPortIdentifiers();
		while (thePorts.hasMoreElements()) {
			CommPortIdentifier com = (CommPortIdentifier) thePorts
					.nextElement();
			switch (com.getPortType()) {
			case CommPortIdentifier.PORT_SERIAL:
				try {
					CommPort thePort = com.open("CommUtil", 50);
					thePort.close();
					h.add(com);
				} catch (PortInUseException e) {
					LOGGER.log(Level.WARNING, "Port, " + com.getName()
							+ ", is in use.");
				} catch (Exception e) {
					System.err.println("Failed to open port " + com.getName());
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
		return h;
	}

	/**
	 * Attempts to connect to the Arduino on the given port name at the given
	 * baud rate. A failure to connect can occur if the port is already in use,
	 * the port is not available, the baud rate is nonstandard, or the Arduino
	 * is already connected.
	 * 
	 * @param port_name
	 *            Title of the connected USB port.
	 * @param baud_rate
	 *            Baud rate, in bits per second.
	 * @return Status of connection - true if successful, false otherwise.
	 * 
	 *         TODO: incorporate logging features for finer detailed error
	 *         messages
	 */
	public boolean connectArduino(String port_name, int baud_rate) {
		if (!hardware.containsKey(port_name)) {
			PowerOutputs power_arduino = new PowerOutputs(port_name, 1000,
					baud_rate);
			power_arduino.initalize();
			power_arduino.addHardwareListener(this);

			hardware.put(port_name, power_arduino);
			ArduinoTimer timer = new ArduinoTimer(port_name);
			scheduler.scheduleAtFixedRate(timer, 500, 100);
			panel.setStatus(Connections.ARDUINO, true);
		}

		return true;
	}

	private class ArduinoTimer extends TimerTask {
		String port_name;

		public ArduinoTimer(String port_name) {
			this.port_name = port_name;
		}

		/**
		 * Constant thread that sends formatted messages to the current Arduino
		 * port.
		 */
		@Override
		public void run() {
			for (Entry<String, SerialHardware> entry : hardware.entrySet()) {
				if (entry.getValue() instanceof Motors) {
					Motors motors = (Motors) entry.getValue();
					motors.update(motion);
				}
			}

			if (hardware.containsKey(port_name)) {
				Arduino arduino = (Arduino) hardware.get(port_name);

				byte msg[] = new byte[14];
				msg[0] = msg[1] = msg[12] = msg[13] = '*';
				for (int n = 0; n < 10; n++)
					msg[n + 2] = (byte) (pins_do[n] ? 'h' : 'l');

				arduino.write(msg);
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.auvua.catfish.HardwareEventListener#hardwareEvent(org.auvua.catfish
	 *      .HardwareEvent)
	 */
	@Override
	public void hardwareEvent(HardwareEvent event) {
		SerialHardware obj = (SerialHardware) event.getSource();

		// TODO: move pins into PowerOutputs
		if (obj instanceof PowerOutputs) {
			char[] data = event.data;
			for (int i = 1; i < 5; i++)
				panel.setDigitalInput(i + 9, (data[i] == 1 ? true : false));

			for (int i = 5; i < 17; i += 2) {
				char highbyte = (char) (data[i] & 0x00ff);
				char lowbyte = (char) (data[i + 1] & 0x00ff);
				panel.setAnalogInput((i - 5) / 2, ((highbyte * 256) + lowbyte));
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.auvua.catfish.ControllerEventListener#controllerEvent(org.auvua.catfish
	 *      .ControllerEvent)
	 */
	@Override
	public void controllerEvent(ControllerEvent event) {
		Controller controller = (Controller) event.getSource();

		switch (event.getControlType()) {
		case Movement:
			break;
		case Acuator:
			break;
		case Sensor:
			break;
		case None:
		default:
			break;
		}
	}
}
