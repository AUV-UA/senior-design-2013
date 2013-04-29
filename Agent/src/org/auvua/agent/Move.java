package org.auvua.agent;

public class Move implements Action {

	private Location start;
	private Location dest;
	private float angle_to;
	private float dist_to;
	
	public Move(Location start, Location dest) {
		this.start = start;
		this.dest = dest;
	}
	
	@Override
	public void initialize() {
		angle_to = start.getHeadingToLocation(dest);
		dist_to = start.getDistanceToLocation(dest);
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
