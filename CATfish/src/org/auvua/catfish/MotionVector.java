package org.auvua.catfish;

public class MotionVector {
	private final static int MAX_VALUE = 100;
	private final static int MIN_VALUE = -100;

	private float x;
	private float y;
	private float z;
	private float a;

	public MotionVector() {
		this(0f, 0f, 0f);
	}

	// TODO: Add support for magnitude and angles
	public MotionVector(float magnitude, float theta, float phi) {
	}

	public MotionVector(float x, float y, float z, float a) {
		setXComponent(x);
		setYComponent(y);
		setZComponent(z);
		setAComponent(a);
	}

	public float getXComponent() {
		return x;
	}

	public void setXComponent(float x) {
		if (x > MAX_VALUE)
			this.x = MAX_VALUE;
		else if (x < MIN_VALUE)
			this.x = MIN_VALUE;
		else
			this.x = x;
	}

	public float getYComponent() {
		return y;
	}

	public void setYComponent(float y) {
		if (y > MAX_VALUE)
			this.y = MAX_VALUE;
		else if (y < MIN_VALUE)
			this.y = MIN_VALUE;
		else
			this.y = y;
	}

	public float getZComponent() {
		return z;
	}

	public void setZComponent(float z) {
		if (z > MAX_VALUE)
			this.z = MAX_VALUE;
		else if (z < MIN_VALUE)
			this.z = MIN_VALUE;
		else
			this.z = z;
	}
	
	public float getAComponent() {
		return a;
	}

	public void setAComponent(float a) {
		if (a > MAX_VALUE)
			this.a = MAX_VALUE;
		else if (a < MIN_VALUE)
			this.a = MIN_VALUE;
		else
			this.a = a;
	}

	public float dotproduct(MotionVector v) {
		float vx = v.getXComponent();
		float vy = v.getYComponent();
		float vz = v.getZComponent();

		return (x * vx) + (vy * y) + (vz * z);
	}

	public MotionVector crossproduct(MotionVector v) {
		float vx = v.getXComponent();
		float vy = v.getYComponent();
		float vz = v.getZComponent();

		float nx = (y * vz) - (z * vy);
		float ny = (z * vx) - (x * vz);
		float nz = (x * vy) - (y * vx);

		return new MotionVector(nx, ny, nz);
	}

	public long getMagnitude() {
		return Math.round(Math.sqrt(x * x + y * y + z * z));
	}
}
