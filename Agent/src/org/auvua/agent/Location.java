package org.auvua.agent;

public class Location {
	public float x;			//meters
	public float y;			//meters
	public float z;			//meters
	public float angle;		//degrees
	
	public Location() {
		this(0.0f, 0.0f, 0.0f, 0.0f);
	}

	public Location(float x, float y, float z, float angle) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.angle = angle;
	}
	
	public float getHeadingToLocation(Location loc) {
		return (angle - loc.angle);
	}
	
	public float getDistanceToLocation(Location loc) {
		return (float) Math.sqrt(Math.pow(x - loc.x, 2) + Math.pow(x - loc.x, 2) + Math.pow(x - loc.x, 2)); 
	}
	
}
