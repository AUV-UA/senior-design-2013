package org.auvua.catfish;

import java.util.EventObject;
import net.java.games.input.*;

/**
 * Custom JoystickEvent for joystick hardware events.
 * 
 * @author forbesk
 */
public class JoystickEvent extends EventObject {
	private static final long serialVersionUID = 5676445947125408486L;
	public Component component;
	private boolean pressed;
	private double value;

	public JoystickEvent(Object source, Component component) {
		super(source);
		this.component = component;
		value = component.getPollData();
		
		if(!component.isAnalog() && value == 1.0f)
			pressed = true;
	}
	
	/**
	 * @return	State of the button (false if event was triggered by axis).
	 */
	public boolean isPressed() {
		return pressed;
	}
	
	/**
	 * @return	Triggering Component's String name.
	 */
	public String getComponentName() {
		return component.getName();
	}

}
