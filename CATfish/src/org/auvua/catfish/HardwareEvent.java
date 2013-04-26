package org.auvua.catfish;

import java.util.EventObject;

/**
 * Custom HardwareEvent for serial hardware events.
 * 
 * @author erbriones
 */
public class HardwareEvent extends EventObject {
	private static final long serialVersionUID = 4592297161275407558L;
	public Object[] data;

	public HardwareEvent(Object object, Object[] data2) {
		super(object);
		this.data = data2;
	}
}
