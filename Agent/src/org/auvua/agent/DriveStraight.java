package org.auvua.agent;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.auvua.utils.protobuffer.AUVprotocol.AUVState;

public class DriveStraight extends Task {

	public DriveStraight() {
		super();
		name = "DriveStraight";
		num_params = 2;
	}

	@Override
	public void run() {
		float distance = (Float) params.get(0).getData();
		float speed = (Float) params.get(1).getData();
		
		long timeout = System.currentTimeMillis() + (long)(1000.0f*distance + 5000.0f/speed);
		
		LOGGER.info("Starting task: " + name);
		
		while (!isFinished()) {
			if (current_state.isEmpty())
				continue;
			
			AUVState state = current_state.remove();
			
			output = buildAUVCommand(0.0f, 1.0f, 0.0f, 0.0f, speed);

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				LOGGER.warning("Task forced to close. Stopping the robot...");
				return;
			}
			
			finished = (System.currentTimeMillis() > timeout);
		}
		output = buildAUVCommand(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		
		LOGGER.info("Task '" + name + "' complete");
	}

}
