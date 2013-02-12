package org.auvua.catfish;

import gnu.io.CommPortIdentifier;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.logging.Logger;

public class Main {

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
					Logger.getGlobal().info("Panel initialized");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				model = new CATFishModel();
				Logger.getGlobal().info("Model initialized");
				
				Iterator<CommPortIdentifier> portsSet = model.getAvailableSerialPorts().iterator();
				Logger.getGlobal().info("Ports scanned");
			}
		});
	}

}
