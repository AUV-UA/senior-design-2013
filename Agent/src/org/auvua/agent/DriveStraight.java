package org.auvua.agent;

public class DriveStraight extends Task {

	public DriveStraight() {
		finished = false;
		name = "DriveStraight";
		num_params = 2;
	}
	
	@Override
	public void run() {
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
