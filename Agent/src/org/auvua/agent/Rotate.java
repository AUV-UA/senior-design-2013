package org.auvua.agent;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.auvua.utils.protobuffer.AUVprotocol.AUVState;

public class Rotate extends Task {
	
	public Rotate() {
		super();
		name = "Rotate";
		num_params = 2;
	}

	@Override
	public void run() {
		float angle = (Float) params.get(0).getData();
		float speed = (Float) params.get(1).getData();
		
		LOGGER.info("Starting task: " + name);
		
		while(current_state.isEmpty());
		
		AUVState state = current_state.remove();
		float target_angle = state.getTelemetry().getHeading() + angle;
		target_angle = target_angle > 360 ? target_angle - 360 : target_angle;
		target_angle = target_angle < 0 ? target_angle + 360 : target_angle;
		
		while (!isFinished()) {
			if (current_state.isEmpty())
				continue;
			
			state = current_state.remove();
			
			float rotation = target_angle - state.getTelemetry().getHeading();
			rotation = rotation > 180 ? rotation - 360 : rotation;
			rotation = rotation < -180 ? rotation + 360 : rotation;
			
			output = buildAUVCommand(0.0f, 0.0f, 0.0f, 
					(rotation < 0 ? -1.0f : 1.0f), speed);

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				LOGGER.warning("Task forced to close. Stopping the robot...");
				return;
			}
			
			finished = Math.abs(rotation) < 5.0f;
		}
		output = buildAUVCommand(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		
		LOGGER.info("Task '" + name + "' complete");
	}

}
