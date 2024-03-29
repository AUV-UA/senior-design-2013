package org.auvua.catfish;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.auvua.catfish.CATFishPanel.Connections;

import org.auvua.utils.protobuffer.AUVprotocol.AUVState;
import org.auvua.utils.*;

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

	private AUVDispatch dispatcher;
	private AUVController auv_controller;
	private int publish_period = 100;	//100 ms
	
	/* Hardware */
	private HashMap<String, SerialHardware> hardware;
	private Timer scheduler;

	/* Current desired motion of the robot */
	public MotionVector motion;

	public float heading;
	public float pitch;
	public float roll;
	public float depth;
	public float battery;
	
	public boolean auto_enabled = false;

	/* Arduino digital outputs and digital/analog inputs*/
	public boolean pins_do[];
	public boolean pins_di[];
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
		
		auv_controller = new AUVController();
		auv_controller.addControllerListener(this);
		
		dispatcher = new AUVDispatch("ipc:///tmp/catfish_state", "ipc:///tmp/catfish_command");
		dispatcher.subscribe(auv_controller, "AUVCommand");
		
	    LOGGER.info("ZMQ outward messages bound to '/tmp/CATFISH'");
	    
	    TimerTask sendAUVStateTask = new TimerTask() {
			@Override
			public void run() {
				AUVState state = buildZMQMsg();
				dispatcher.sendState(state, "AUVState");
			}
	    };
	    
	    scheduler.scheduleAtFixedRate(sendAUVStateTask, 100, publish_period);
	    LOGGER.info("Messages being pushed to '/tmp/CATFISH' every " + publish_period + "ms"); 
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

	public boolean connectPowerOutputs(String port_name, int baud_rate) {
		if (!hardware.containsKey(port_name)) {
			PowerOutputs power_arduino = new PowerOutputs(port_name, 1000,
					baud_rate, this);
			power_arduino.initialize();
			power_arduino.addHardwareListener(this);

			hardware.put(port_name, power_arduino);
			power_arduino.scheduleAtFixedRate(500, 50);
			panel.setStatus(Connections.ARDUINO, true);
			LOGGER.info("Power outputs connected.");
		}

		return true;
	}

	public boolean connectMotors(String port_name, int baud_rate) {
		if (!hardware.containsKey(port_name)) {
			Motors motors_arduino = new Motors(port_name, 1000, baud_rate, this);
			motors_arduino.initialize();
			motors_arduino.addHardwareListener(this);

			hardware.put(port_name, motors_arduino);
			motors_arduino.scheduleAtFixedRate(500, 50);
			panel.setStatus(Connections.MOTORS, true);
			LOGGER.info("Motors connected.");
		}

		return true;
	}

	public boolean connectCompass(String port_name, int baud_rate) {
		if (!hardware.containsKey(port_name)) {
			System.out.println("Creating motors arduino and starting task...");
			Compass compass = new Compass(port_name, 1000, baud_rate);
			compass.initialize();
			compass.addHardwareListener(this);

			hardware.put(port_name, compass);
			panel.setStatus(Connections.COMPASS, true);
			LOGGER.info("Compass connected.");
		}

		return true;
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
			Object[] data = event.data;
			for (int i = 1; i < 5; i++)
				panel.setDigitalInput(i + 9, ((Character) data[i] == 1 ? true
						: false));

			for (int i = 5; i < 17; i += 2) {
				char highbyte = (char) (((Character) data[i]) & 0x00ff);
				char lowbyte = (char) (((Character) data[i + 1]) & 0x00ff);
				panel.setAnalogInput((i - 5) / 2, ((highbyte * 256) + lowbyte));
			}
		}

		if (obj instanceof Compass) {
			heading = (Float) event.data[0];
			pitch = (Float) event.data[1];
			roll = (Float) event.data[2];
			panel.setCompass(heading, pitch, roll);
		}

		if (obj instanceof Motors) {
			depth = (Float) event.data[0];
			battery = (Float) event.data[1];
			panel.setDepth(depth);
			panel.setBattery(battery);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.auvua.catfish.ControllerEventListener#controllerEvent(org.auvua.catfish
	 *      .ControllerEvent) checksum
	 */
	@Override
	public void controllerEvent(ControllerEvent event) {
		Controller controller = (Controller) event.getSource();

		switch (event.getControlType()) {
		case Movement:
			// update motion vector
			motion = (MotionVector) event.getData();
			System.out.println(motion.toString());
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
	
	public AUVState buildZMQMsg() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		AUVState.Builder state = AUVState.newBuilder();
		
		state.setTimestamp(System.currentTimeMillis())
			.setMission(false)
			.setBatt(battery)
			.setAligning(false)
			.setTelemetry(AUVState.Telemetry.newBuilder()
					.setHeading(heading)
					.setPitch(pitch)
					.setRoll(roll)
					.setDepth(depth));
		
		AUVState result = state.build();
		
		return result;
	}

}
