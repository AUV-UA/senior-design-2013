package org.auvua.catfish.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.auvua.catfish.ControllerEvent;
import org.auvua.catfish.ControllerEventListener;
import org.auvua.catfish.KeyboardController;

public class KeyboardControllerTest {
	private KeyboardController cntrl;
	private TestControllerListener tester;

	private class TestControllerListener implements ControllerEventListener {
		@Override
		public void controllerEvent(ControllerEvent event) {
		}
	}

	@Before
	public void setup() {
		cntrl = new KeyboardController();
		tester = new TestControllerListener();

		cntrl.addControllerListener(tester);
	}

	@Test
	public void testKeyPress() {

		// FakeEvent.sendKeyPress();

		fail("Not yet implemented");
	}
}
