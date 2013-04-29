package org.auvua.utils;

import java.util.EventObject;
import org.auvua.utils.protobuffer.AUVprotocol.AUVState;

public class AUVStateEvent extends EventObject {
	private static final long serialVersionUID = 5212443574858752099L;
	private AUVState state;

	public AUVStateEvent(Object sender, AUVState state) {
		super(sender);
		this.state = state;
	}

	public AUVState getAUVState() {
		return state;
	}
}
