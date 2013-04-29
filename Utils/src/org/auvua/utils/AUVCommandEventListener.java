package org.auvua.utils;

import org.auvua.utils.protobuffer.AUVprotocol.AUVCommand;

public interface AUVCommandEventListener {
	public void onAUVCommandEvent(AUVCommandEvent event);
}
