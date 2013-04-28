package org.auvua.agent;

public interface Action extends Runnable {
	public void initialize();
	public void start();
	public void run();
	public boolean isFinished();
}
