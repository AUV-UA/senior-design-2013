package org.auvua.catfish;

import java.util.EventObject;

/**
 * Custom Controller listener.
 * 
 * @author erbriones
 * 
 */
public class ControllerEvent extends EventObject {
	private static final long serialVersionUID = 1281238472L;
	private ControlType control_type;

	public ControllerEvent(Object obj, ControlType type) {
		super(obj);
		control_type = type;
	}

	public ControlType getControlType() {
		return control_type;
	}
}
