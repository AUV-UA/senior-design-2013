package org.auvua.catfish;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

import java.util.Enumeration;
import java.util.HashSet;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CATFishModel {
	
	public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private CATFishPanel panel;

	public CATFishModel(CATFishPanel panel) {
		this.panel = panel;
		
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
}
