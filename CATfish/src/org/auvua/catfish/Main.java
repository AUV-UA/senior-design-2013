package org.auvua.catfish;

import java.awt.EventQueue;
import java.util.logging.Logger;

public class Main {

	public static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static CATFishModel model;
	public static CATFishPanel panel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
				LOGGER.info("Model initialized");
				
				model.connectArduino("/dev/ttyUSB0", 9600);
			}
		});
	}

}
