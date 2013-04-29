package org.auvua.catfish;

import org.auvua.utils.AUVCommandEvent;
import org.auvua.utils.AUVCommandEventListener;
import org.auvua.utils.protobuffer.AUVprotocol.AUVCommand;

public class AUVController extends Controller implements AUVCommandEventListener {

	@Override
	public void onAUVCommandEvent(AUVCommandEvent event) {
		AUVCommand command = event.getAUVCommand();
		
		if(command.hasSpeed()) {
			MotionVector move = new MotionVector(command.getSpeed().getX(),
					command.getSpeed().getY(), 
					command.getSpeed().getZ(), 
					command.getSpeed().getYaw(),
					command.getSpeed().getPower());
			sendControllerEvent(ControlType.Movement, move);
		}
	}
	
}
