package org.auvua.catfish;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeyboardController extends Controller implements KeyListener {
	Queue<MotionVector> movement_queue;

	public KeyboardController() {
		movement_queue = new ConcurrentLinkedQueue<MotionVector>();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		MotionVector m = null;

		switch (event.getKeyChar()) {
		case 'W':
			m = new MotionVector();
			break;
		case 'S':
			m = new MotionVector();
			break;
		case 'A':
			if (event.isShiftDown())
				m = new MotionVector();
			else
				m = new MotionVector();
			break;
		case 'D':
			if (event.isShiftDown())
				m = new MotionVector();
			else
				m = new MotionVector();
			break;
		}

		if (m == null) {
			movement_queue.add(m);
			this.sendControllerEvent(ControlType.Movement);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		this.sendControllerEvent(ControlType.Movement);
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	public MotionVector getLastMovement() {
		if (movement_queue.isEmpty())
			return null;

		return movement_queue.remove();
	}
}
