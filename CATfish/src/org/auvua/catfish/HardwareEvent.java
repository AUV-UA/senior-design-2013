package org.auvua.catfish;

import java.util.EventObject;

public class HardwareEvent extends EventObject {
	private static final long serialVersionUID = 4592297161275407558L;
	public String data;

	public HardwareEvent(Object object, String data) {
		super(object);
		this.data = data;
	}
}
