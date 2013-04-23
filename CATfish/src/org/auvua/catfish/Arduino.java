package org.auvua.catfish;

import java.util.Timer;
import java.util.TimerTask;

import gnu.io.*;

public abstract class Arduino extends SerialHardware {
	private ArduinoTimerTask task;

	public Arduino(String port_name, int timeout, int baud_rate) {
		super(port_name, timeout, baud_rate, SerialPort.DATABITS_8,
				SerialPort.PARITY_NONE, SerialPort.STOPBITS_1);

		task = new ArduinoTimerTask();
	}

	public abstract void backgroundTask();

	public final void scheduleAtFixedRate(long delay, long period) {
		if (!task.scheduled)
			task.timer.scheduleAtFixedRate(task, delay, period);
	}

	public final void stop() {
		if (task.scheduled)
			task.timer.cancel();
	}

	private final class ArduinoTimerTask extends TimerTask {
		private Timer timer;
		private boolean scheduled;

		public ArduinoTimerTask() {
			timer = new Timer();
			scheduled = false;
		}

		@Override
		public void run() {
			backgroundTask();
		}
	}
}
