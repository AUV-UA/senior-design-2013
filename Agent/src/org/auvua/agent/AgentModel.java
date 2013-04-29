package org.auvua.agent;

import java.util.logging.Logger;

public class AgentModel {
	
	/* Global Java logger reference */
	public static final Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private AgentPanel panel;
	private Mission mission;
	
	public AgentModel() {
		LOGGER.info("Model initialized");
	}
	
	public void setPanel(AgentPanel panel) {
		this.panel = panel;
	}
	
	public void startMission(String file) {
		mission = new Mission(file);
		mission.start();
	}
	
	public void stopMission() {
		mission.stop();
	}
	
}
