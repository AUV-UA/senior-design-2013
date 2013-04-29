package org.auvua.agent;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.auvua.utils.protobuffer.AUVprotocol.AUVState;

public class DriveStraight extends Task {

	public DriveStraight() {
		finished = false;
		name = "DriveStraight";
		num_params = 2;
		current_state = new ConcurrentLinkedQueue<AUVState>();
	}

	@Override
	public void run() {
		while (!isFinished()) {
			if (current_state.isEmpty())
				continue;
			
			AUVState state = current_state.remove();
			
			LOGGER.info("Starting task: " + name);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOGGER.warning("Task forced to close. Stopping the robot...");
				return;
			}
			
			LOGGER.info("Task '" + name + "' complete");
			finished = true;
		}
	}

}
