package org.auvua.catfish;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import org.auvua.catfish.CATFishPanel.Connections;

/** 
 * Collects data from telemetry sources, writes values to motors, and 
 * interfaces with the CATFishPanel to deliver information to user. 
 *  
 * @author forbesk
 * @author erbriones
 */
public class CATFishModel implements HardwareEventListener, JoystickEventListener {
	
	/** Global Java logger reference */
	public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private CATFishPanel panel;
	private SerialHardware arduino;
	private SerialHardware compass;
	private SerialHardware motors;
	private Joystick joystick;
	private Timer timer_ard;
	private boolean connected_ard;
	private boolean connected_comp;
	private boolean connected_joy;
	private boolean connected_motors;
	/**	Arduino digital outputs */
	public boolean pins_do[];	
	/** Arduino digital inputs */
	public boolean pins_di[];	
	/** Arduino analog inputs */
	public int pins_ai[];		

	/** 
	 * Initializes data and collects initial list of available serial ports.
	 * 
	 * @param panel		Associated CATFishPanel object (view/controller of MVC paradigm)
	 */
	public CATFishModel(CATFishPanel panel) {
		this.panel = panel;
		
		pins_do = new boolean[10];
		pins_di = new boolean[4];
		pins_ai = new int[6];
		
		//check for generic comm ports
		HashSet<CommPortIdentifier> ports = getAvailableSerialPorts();
		if(ports.size() > 0 ) {
			for(CommPortIdentifier port: ports) {
				LOGGER.info("USB port available: " + port.getName());
			}
			panel.setAvailableSerialPorts(ports);
		} else {
			LOGGER.info("No USB ports available.");
		}
		
		//check for joysticks, gamepads, etc.
		HashSet<Controller> controllers = getAvailableControllers();
		if(controllers.size() > 0) {
			for(Controller c: controllers) 
				LOGGER.info("Controller available: " + c.getPortNumber() + " - " + c.getName());
			panel.setAvailableControllers(controllers);
		} else {
			LOGGER.info("No controllers available.");
		}
	}
	
	/** 
	 * Identifies and returns a hashset of all available Comm ports.
     * 
     * @return    A HashSet containing the CommPortIdentifier for all serial ports that are not currently being used.
     */
    public HashSet<CommPortIdentifier> getAvailableSerialPorts() {
        HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
        Enumeration<?> thePorts = CommPortIdentifier.getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
	            case CommPortIdentifier.PORT_SERIAL:
	                try {
	                    CommPort thePort = com.open("CommUtil", 50);
	                    thePort.close();
	                    h.add(com);
	                } catch (PortInUseException e) {
	                   LOGGER.log(Level.WARNING, "Port, "  + com.getName() + ", is in use."); 
	                } catch (Exception e) {
	                    System.err.println("Failed to open port " +  com.getName());
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
	 * Identifies and returns a hashset of all available controllers seen by JInput api.
     * 
     * @return    A HashSet containing the Controller reference for all controllers in the environment.
     */
    public HashSet<Controller> getAvailableControllers() {
    	HashSet<Controller> h = new HashSet<Controller>();
    	ControllerEnvironment env = ControllerEnvironment.getDefaultEnvironment();
    	Controller[] controllers = env.getControllers();
    	
    	for(int i = 0; i < controllers.length; i++)
    		h.add(controllers[i]);
    	
    	return h;
    }
    
    /** 
     * Attempts to connect to the Arduino on the given port name at the given baud rate.
     * A failure to connect can occur if the port is already in use, the port is not
     * available, the baud rate is nonstandard, or the Arduino is already connected.
     *  
     * @param port_name		Title of the connected USB port.
     * @param baud_rate		Baud rate, in bits per second.
     * @return				Status of connection - true if successful, false otherwise.
     * 
     * TODO: incorporate logging features for finer detailed error messages 
     */
    public boolean connectArduino(String port_name, int baud_rate) {
    	if(!connected_ard) {
	    	arduino = new Arduino(port_name, 1000, baud_rate);
	    	arduino.initalize();
	    	arduino.addHardwareListener(this);
	    	if(timer_ard != null) {
	    		timer_ard.cancel();
	    	} else {
	    		timer_ard = new Timer();
	    	}
	    	timer_ard.scheduleAtFixedRate(new ArduinoTimer(), 500, 100);
	    	connected_ard = true;
	    	panel.setStatus(Connections.ARDUINO, true);
	    	return true;
    	}
    	return false;
    }
    
    /**
     * Attempts to connect the chosen joystick. Joysticks are referenced by their 
     * Controller port number. If the controller does not exist or a joystick
     * is already connected, the connecting process will fail and return false;
     * @param port_number		Controller joystick port number reference.
     * @return					True if connected. False otherwise.
     */
    public boolean connectJoystick(int port_number) {
    	if(!connected_joy) {
    		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
    		Controller c = null;
    		System.out.println("Checking for port number: " + port_number);
    		for(int i = 0; i < controllers.length; i++) {
    			if(controllers[i].getPortNumber() == port_number) {
    				c = controllers[i];
    				break;
    			}
    		}
    		if(c == null) return false;
    		joystick = new Joystick(c);
    		joystick.setJoystickListener(this);
    		return true;
    	}
    	return false;
    }
    
    /** 
     * Constant thread that sends formatted messages to the current Arduino port.
     * 
     * @author forbesk
     */
    private class ArduinoTimer extends TimerTask {
		@Override
		public void run() {
			if(connected_ard) {
				byte msg[] = new byte[14];
				msg[0] = msg[1] = msg[12] = msg[13] = '*';
				for(int n = 0; n < 10; n++)
					msg[n+2] = (byte) (pins_do[n] ? 'h' : 'l');
				arduino.write(msg);
			}
		}
    }

	/* (non-Javadoc)
	 * @see org.auvua.catfish.HardwareEventListener#hardwareEvent(org.auvua.catfish.HardwareEvent)
	 */
	@Override
	public void hardwareEvent(HardwareEvent event) {
		//Arduino input
		if(event.getSource().equals(arduino)) {
			char[] data = event.data;
			for(int i = 1; i < 5; i++)
				panel.setDigitalInput(i+9, (data[i] == 1 ? true : false));
			
			for(int i = 5; i < 17; i+=2) {
				char highbyte = (char)(data[i] & 0x00ff);
				char lowbyte = (char)(data[i+1] & 0x00ff);
				panel.setAnalogInput((i-5)/2, ((highbyte * 256) + lowbyte));
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.auvua.catfish.JoystickEventListener#joystickEvent(org.auvua.catfish.JoystickEvent)
	 */
	@Override
	public void joystickEvent(JoystickEvent event) {
		System.out.println(event.getComponentName() + ": " + event.isPressed());		
	}
}
