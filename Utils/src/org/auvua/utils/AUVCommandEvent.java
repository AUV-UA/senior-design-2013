package org.auvua.utils;

import java.util.EventObject;
import org.auvua.utils.protobuffer.AUVprotocol.AUVCommand;

public class AUVCommandEvent extends EventObject {
	private static final long serialVersionUID = -7321261155032715365L;
	final AUVCommand command;
	
	public AUVCommandEvent(Object sender, AUVCommand command) {
		super(sender);
		this.command = command;
	}

	public AUVCommand getAUVCommand() {
		return command;
	}
}
