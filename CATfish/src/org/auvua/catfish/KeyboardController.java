package org.auvua.catfish;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map.Entry;

public class KeyboardController extends Controller {
	HashMap<Integer, Boolean> keymap;
	final HashMap<Integer, MotionVector> motionmap;

	public KeyboardController() {
		super();
		keymap = new HashMap<Integer, Boolean>();
		motionmap = new HashMap<Integer, MotionVector>();
		motionmap.put(KeyEvent.VK_W, new MotionVector(0f, 1f, 0f, 0f, 1f));
		motionmap.put(KeyEvent.VK_A, new MotionVector(-1f, 0f, 0f, 0f, 1f));
		motionmap.put(KeyEvent.VK_S, new MotionVector(0f, -1f, 0f, 0f, 1f));
		motionmap.put(KeyEvent.VK_D, new MotionVector(1f, 0f, 0f, 0f, 1f));
		motionmap.put(KeyEvent.VK_UP, new MotionVector(0f, 0f, 1f, 0f, 1f));
		motionmap.put(KeyEvent.VK_LEFT, new MotionVector(0f, 0f, 0f, -1f, 1f));
		motionmap.put(KeyEvent.VK_DOWN, new MotionVector(0f, 0f, -1f, 0f, 1f));
		motionmap.put(KeyEvent.VK_RIGHT, new MotionVector(0f, 0f, 0f, 1f, 1f));
	}

	public void keyPressed(KeyEvent event) {
		System.out.println("Key pressed");
		if(keymap.containsKey(event.getKeyCode()) && keymap.get(event.getKeyCode()))
			return;
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
				updateMotion();
				break;
			default:
				break;
		}
	}

	public void updateMotion() {
		MotionVector m = new MotionVector(0f, 0f, 0f, 0f, 100.0f);

		for (Entry<Integer, Boolean> entry : keymap.entrySet()) {
			if (motionmap.containsKey(entry.getKey()) && entry.getValue()) {
				m.addVector(motionmap.get(entry.getKey()));
			}
		}
		
		sendControllerEvent(ControlType.Movement, m);
	}

	public void keyReleased(KeyEvent event) {
		keymap.put(event.getKeyCode(), false);

			switch (event.getKeyCode()) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_S:
			case KeyEvent.VK_A:
			case KeyEvent.VK_D:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_LEFT:
				updateMotion();
				break;
			default:
				break;
		}
	}
}
