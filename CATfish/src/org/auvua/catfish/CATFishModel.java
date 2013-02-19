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
	public boolean pins_do[];
	public boolean pins_di[];
	public int pins_ai[];

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
}
