package org.auvua.catfish;

public class MotionVector {
	private final static int MAX_VALUE = 127;
	private final static int MIN_VALUE = -127;

	private int x;
	private int y;
	private int z;

	public int getXComponent() {
		return x;
	}

	public void setXComponent(int x) {
		if (x > MAX_VALUE)
			this.x = MAX_VALUE;
		else if (x < MIN_VALUE)
			this.x = MIN_VALUE;
		else
			this.x = x;
	}

	public int getYComponent() {
		return y;
	}

	public void setYComponent(int y) {
		if (y > MAX_VALUE)
			this.y = MAX_VALUE;
		else if (y < MIN_VALUE)
			this.y = MIN_VALUE;
		else
			this.y = y;
	}

	public int getZComponent() {
		return z;
	}

	public void setZComponent(int z) {
		if (z > MAX_VALUE)
			this.z = MAX_VALUE;
		else if (z < MIN_VALUE)
			this.z = MIN_VALUE;
		else
			this.z = z;
	}
}
