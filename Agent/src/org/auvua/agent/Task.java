package org.auvua.agent;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import org.auvua.utils.protobuffer.AUVprotocol.AUVCommand;
import org.auvua.utils.protobuffer.AUVprotocol.AUVState;

public abstract class Task implements Runnable {

	public static final Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);
	protected ArrayList<Parameter> params;
	protected ConcurrentLinkedQueue<AUVState> current_state;
	protected AUVCommand output;
	protected int num_params;
	protected String name;
	protected Location start;
	protected boolean finished;

	protected Task() {
		params = new ArrayList<Parameter>();
		current_state = new ConcurrentLinkedQueue<AUVState>();
		output = null;
		finished = false;
	}

	public final void queueState(AUVState state) {
		current_state.add(state);
	}
	
	public final AUVCommand getOutput() {
		return output;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setStartLocation(Location start) {
		this.start = start;
	}

	public void setParameters(ArrayList<Parameter> params) {
		this.params = params;
	}
	
	public AUVCommand buildAUVCommand(float x, float y, float z, float rot, float speed) {
		AUVCommand.Builder command = AUVCommand.newBuilder();
		command.setSpeed(AUVCommand.Movement.newBuilder()
				.setX(x)
				.setY(y)
				.setZ(z)
				.setYaw(rot)
				.setPower(speed));
		
		return command.build();
	}
}
