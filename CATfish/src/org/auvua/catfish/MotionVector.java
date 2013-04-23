package org.auvua.catfish;

public class MotionVector {
	private final static int MAX_VALUE = 100;
	private final static int MIN_VALUE = -100;

	private float x;
	private float y;
	private float z;
	private float a;
	private float scaling;

	public MotionVector() {
		this(0f, 0f, 0f, 0f, 1f);
	}

	// TODO: Add support for magnitude and angles
	public MotionVector(float magnitude, float theta, float phi) {
	}

	public MotionVector(float x, float y, float z, float a, float scaling) {
		setXComponent(x);
		setYComponent(y);
		setZComponent(z);
		setAComponent(a);
	}

	public float getXComponent() {
		return x;
	}

	public void setXComponent(float x) {
		this.x = x;
	}

	public float getYComponent() {
		return y;
	}

	public void setYComponent(float y) {
		this.y = y;
	}

	public float getZComponent() {
		return z;
	}

	public void setZComponent(float z) {
		this.z = z;
	}

	public float getAComponent() {
		return a;
	}

	public void setAComponent(float a) {
		this.a = a;
	}
	
	public float getScaling() {
		return scaling;
	}
	
	public void addVector(MotionVector v) {
		x += v.getXComponent();
		y += v.getYComponent();
		z += v.getZComponent();
		a += v.getAComponent();
	}

	public float dotproduct(MotionVector v) {
		float vx = v.getXComponent();
		float vy = v.getYComponent();
		float vz = v.getZComponent();

		return (x * vx) + (vy * y) + (vz * z);
	}

	public MotionVector crossproduct(MotionVector v) {
		float vx = v.getXComponent() / v.getMagnitude();
		float vy = v.getYComponent() / v.getMagnitude();
		float vz = v.getZComponent() / v.getMagnitude();

		float nx = (y / getMagnitude() * vz) - (z / getMagnitude() * vy);
		float ny = (z / getMagnitude() * vx) - (x / getMagnitude() * vz);
		float nz = (x / getMagnitude() * vy) - (y / getMagnitude() * vx);

		return new MotionVector(MAX_VALUE * nx, MAX_VALUE * ny, MAX_VALUE * nz);
	}

	public float getMagnitude() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public void normalize() {
		x /= getMagnitude();
		y /= getMagnitude();
		z /= getMagnitude();
	}
	
	public boolean isNormalized() {
		return (Math.abs(1 - Math.sqrt(x * x + y * y + z * z)) < 0.001);
	}
}
