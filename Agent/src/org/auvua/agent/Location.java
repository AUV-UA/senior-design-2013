package org.auvua.agent;

public class Location {
	public float x;
	public float y;
	public float z;
	public float angle;
	
	public Location() {
		this(0.0f, 0.0f, 0.0f, 0.0f);
	}

	public Location(float x, float y, float z, float angle) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.angle = angle;
	}
	
}
