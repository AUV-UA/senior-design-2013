package org.auvua.agent;

import java.util.ArrayList;
import java.util.logging.Logger;

public abstract class Task implements Runnable {

	public static final Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);
	protected ArrayList<Parameter> params;
	protected int num_params;
	protected String name;
	protected Location start;
	protected boolean finished;
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setStartLocation(Location start) {
		this.start = start;
	}
	
	public void setParameters(ArrayList<Parameter> params) {
		this.params = params;
	}

}
