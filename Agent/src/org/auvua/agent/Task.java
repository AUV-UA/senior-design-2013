package org.auvua.agent;

import java.util.ArrayList;

public abstract class Task implements Runnable {

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
