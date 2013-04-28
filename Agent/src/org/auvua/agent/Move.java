package org.auvua.agent;

public class Move implements Action {

	private Location start;
	private Location dest;
	
	public Move(Location start, Location dest) {
		this.start = start;
		this.dest = dest;
	}
	
	@Override
	public void initialize() {
		
	}

	@Override
	public void start() {
		
	}

	@Override
	public void run() {
		
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}
