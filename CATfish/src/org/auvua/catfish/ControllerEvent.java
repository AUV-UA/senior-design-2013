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
	private Object data;

	public ControllerEvent(Object obj, ControlType type, Object data) {
		super(obj);
		control_type = type;
		this.data = data;
	}

	public ControlType getControlType() {
		return control_type;
	}

	public Object getData() {
		return data;
	}

}
