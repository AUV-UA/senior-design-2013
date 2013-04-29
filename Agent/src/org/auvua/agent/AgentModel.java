package org.auvua.agent;

import java.util.logging.Logger;

import org.auvua.utils.AUVDispatch;
import org.auvua.utils.AUVStateEvent;
import org.auvua.utils.AUVStateEventListener;
import org.auvua.utils.protobuffer.AUVprotocol.AUVState;

public class AgentModel implements AUVStateEventListener {
	
	/* Global Java logger reference */
	public static final Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private AgentPanel panel;
	private Mission mission;
	
	private AUVDispatch dispatcher;
	private AUVState state_curr = null;
	private AUVState state_prev = null;
	
	public AgentModel() {
		LOGGER.info("Model initialized");
		dispatcher = new AUVDispatch("ipc:///tmp/catfish_command", "ipc:///tmp/catfish_state");
		dispatcher.subscribe(this, "AUVState");
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

	@Override
	public void onAUVStateEvent(AUVStateEvent event) {
		//System.out.println("AUV State received at " + System.currentTimeMillis());
		state_prev = state_curr;
		state_curr = event.getAUVState();
		if(state_curr.hasTelemetry() && state_prev != null) {
			System.out.println(""+state_curr.getTimestamp() + " " 
					+ state_curr.getTelemetry().getRoll());
		}
	}
	
}
