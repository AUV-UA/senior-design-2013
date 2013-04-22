package org.auvua.catfish;

import java.util.ArrayList;

public abstract class Controller {

	private ArrayList<ControllerEventListener> controller_listeners;

	public Controller() {
	}

	/**
	 * Adds a listener for controller input signals
	 * 
	 * @param listener
	 *            ControllerEventListener to attach to this Controller.
	 */
	public final void addControllerListener(ControllerEventListener listener) {
		controller_listeners.add(listener);
	}

	/**
	 * Removes the listener on events from the serial port.
	 * 
	 * @param listener
	 *            ControllerEventListener to remove from this Controller.
	 */
	public final void removeControllerListener(ControllerEventListener listener) {
		controller_listeners.remove(listener);
	}

	/**
	 * Generates controller events for each listener
	 * 
	 * @param data
	 *            Array of characters to pass to attached
	 *            ControllerEventListener.
	 */
	protected final synchronized void sendControllerEvent(ControlType type) {
		ControllerEvent event = new ControllerEvent(this, type);

		for (ControllerEventListener listener : controller_listeners) {
			listener.controllerEvent(event);
		}
	}
}
