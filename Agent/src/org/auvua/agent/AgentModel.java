package org.auvua.agent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import org.auvua.utils.AUVDispatch;
import org.auvua.utils.AUVStateEvent;
import org.auvua.utils.AUVStateEventListener;
import org.auvua.utils.protobuffer.AUVprotocol.AUVCommand;
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
	
	private float move_x = 0.0f;
	private float move_y = 0.0f;
	private float move_z = 0.0f;
	private float move_rot = 0.0f;
	
	private boolean mission_enabled = false;
	private boolean catfish_enabled = false;
	
	public AgentModel() {
		LOGGER.info("Model initialized");
		dispatcher = new AUVDispatch("ipc:///tmp/catfish_command", "ipc:///tmp/catfish_state");
		dispatcher.subscribe(this, "AUVState");
		
		Timer scheduler = new Timer();
		TimerTask commandSender = new TimerTask() {
			@Override
			public void run() {
				if(mission != null) {
					Task task = mission.getCurrentTask();
					if(task != null) {
						AUVCommand c = task.getOutput();
						if(c != null) {
							dispatcher.sendCommand(c, "AUVCommand");
						}
					}
				}
			}
		};
		
		scheduler.scheduleAtFixedRate(commandSender, 100, 100);
	}
	
	public void setPanel(AgentPanel panel) {
		this.panel = panel;
	}
	
	public void startMission(String file) {
		if(!catfish_enabled) {
			LOGGER.warning("CATfish is not enabled");
			return;
		}
		mission = new Mission(file);
		mission.start();
		
		mission_enabled = true;
	}
	
	public void stopMission() {
		if(mission_enabled) {
			mission.stop();
			mission_enabled = false;
		} else {
			LOGGER.info("Mission is not running");
		}
	}
	
	@Override
	public void onAUVStateEvent(AUVStateEvent event) {
		//catfish is enabled if this is the third state event
		catfish_enabled = state_prev != null;
		
		state_prev = state_curr;
		state_curr = event.getAUVState();
		
		if(mission != null)
			mission.updateAUVState(state_curr);
	}
	
}
