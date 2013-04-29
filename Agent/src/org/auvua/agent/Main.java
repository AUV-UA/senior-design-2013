package org.auvua.agent;

import java.awt.EventQueue;

public class Main {
	
	public static AgentPanel panel;
	public static AgentModel model;
	
	public static void main(String[] args) throws InterruptedException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					panel = new AgentPanel();
					panel.frmAuvAgent.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				model = new AgentModel();
				
				model.setPanel(panel);
				panel.setModel(model);
			}
		});
		
	}
}
