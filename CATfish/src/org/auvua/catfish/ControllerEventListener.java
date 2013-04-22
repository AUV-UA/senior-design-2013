package org.auvua.catfish;

public interface ControllerEventListener {
	/**
	 * Event received when a controller has updated its state.
	 * 
	 * @param event
	 */
	public void controllerEvent(ControllerEvent event);
}
