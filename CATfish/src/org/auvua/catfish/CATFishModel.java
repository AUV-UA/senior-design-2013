package org.auvua.catfish;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.auvua.catfish.CATFishPanel.Connections;

public class CATFishModel implements HardwareEventListener {
	
	public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private CATFishPanel panel;
	private SerialHardware arduino;
	private SerialHardware compass;
	private SerialHardware motors;
	private Timer timer_ard;
	private boolean connected_ard;
	private boolean connected_comp;
	private boolean connected_joy;
	private boolean connected_motors;
	private boolean pins_do[];
	private boolean pins_di[];
	private int pins_ai[];

	public CATFishModel(CATFishPanel panel) {
		this.panel = panel;
		
		pins_do = new boolean[10];
		pins_di = new boolean[4];
		pins_ai = new int[6];
		
		HashSet<CommPortIdentifier> ports = getAvailableSerialPorts();
		if(ports.size() > 0 ) {
			for(CommPortIdentifier port: ports) {
				LOGGER.info("USB port available: " + port.getName());
			}
			panel.setAvailablePorts(ports);
		} else {
			LOGGER.info("No USB ports available.");
		}
	}
	
	/**
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
    
    public boolean connectArduino(String port_name, int baud_rate) {
    	arduino = new Arduino(port_name, 100, baud_rate);
    	arduino.initalize();
    	arduino.addHardwareListener(this);
    	if(timer_ard != null) {
    		timer_ard.cancel();
    	} else {
    		timer_ard = new Timer();
    	}
    	timer_ard.scheduleAtFixedRate(new ArduinoTimer(), 0, 20);
    	connected_ard = true;
    	panel.setStatus(Connections.ARDUINO, true);
    	return true;
    }
    
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

	@Override
	public void hardwareEvent(HardwareEvent event) {
		LOGGER.info("Harware event");
		if(event.getSource().equals(arduino)) {
			LOGGER.info("Received data from Arduino: " + event.data);
		}
	}
}
