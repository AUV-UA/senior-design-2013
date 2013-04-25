package org.auvua.catfish.test;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class FakeEvent {

	/**
	 * Given a src Component will fake an action event performed.
	 * 
	 * @param src
	 */
	public static void sendActionPerformed(Component src) {
		ActionEvent action_event;
		action_event = new ActionEvent(src, ActionEvent.ACTION_PERFORMED, "");

		scheduleEvent(action_event);
	}

	/**
	 * Given a src Component will fake an key press event.
	 * 
	 * @param src
	 */
	public static void sendKeyPress(Component src, int keyCode) {
		KeyEvent key_event;

		key_event = new KeyEvent(src, KeyEvent.KEY_RELEASED, 0, 0, keyCode,
				KeyEvent.CHAR_UNDEFINED);

		scheduleEvent(key_event);
	}

	/**
	 * Given a src Component will fake an key relase event.
	 * 
	 * @param src
	 */
	public static void sendKeyRelease(Component src, int keyCode) {
		KeyEvent key_event;

		key_event = new KeyEvent(src, KeyEvent.KEY_RELEASED, 0, 0, keyCode,
				KeyEvent.CHAR_UNDEFINED);

		scheduleEvent(key_event);
	}

	/**
	 * Schedules the fake event.
	 * 
	 * @param event
	 */
	private static void scheduleEvent(AWTEvent event) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		EventQueue queue = kit.getSystemEventQueue();
		queue.postEvent(event);
	}
}
