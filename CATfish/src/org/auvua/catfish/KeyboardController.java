package org.auvua.catfish;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyboardController extends Controller implements KeyListener {
	HashMap<Integer, Boolean> keymap;

	public KeyboardController() {
		keymap = new HashMap<Integer, Boolean>();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		MotionVector m = null;
		
		keymap.put(event.getKeyCode(), true);

		switch (event.getKeyCode()) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_S:
			case KeyEvent.VK_A:
			case KeyEvent.VK_D:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_LEFT:
				m = new MotionVector();
				break;
		}

		if (m == null) {
			this.sendControllerEvent(ControlType.Movement, m);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		keymap.put(event.getKeyCode(), false);
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

}
