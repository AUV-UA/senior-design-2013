package org.auvua.catfish;

import java.awt.EventQueue;
import java.io.File;
import java.util.logging.Logger;

import com.stolsvik.tech.keyeventfixer.RepeatingReleasedEventsFixer;

public class Main {

	public static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static CATFishModel model;
	public static CATFishPanel panel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new RepeatingReleasedEventsFixer().install();
		System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0:/dev/ttyS0:/dev/ttyUSB0");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					panel = new CATFishPanel();
					panel.frmCatfish.setVisible(true);
					LOGGER.info("Panel initialized");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				model = new CATFishModel(panel);
				panel.setModel(model);
				
				LOGGER.info("Model initialized");
			}
		});
	}

}
