package org.auvua.agent;

public abstract class Task implements Runnable {

	protected Location start;
	protected Location end;
	protected boolean finished;
	
	public boolean isFinished() {
		return finished;
	}

}
