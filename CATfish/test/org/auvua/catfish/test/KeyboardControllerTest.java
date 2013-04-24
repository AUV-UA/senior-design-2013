package org.auvua.catfish.test;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.auvua.catfish.ControllerEvent;
import org.auvua.catfish.ControllerEventListener;
import org.auvua.catfish.KeyboardController;

public class KeyboardControllerTest {
	private KeyboardTestComponent component;
	private KeyboardController cntrl;
	private TestControllerListener tester;

	private class TestControllerListener implements ControllerEventListener {
		@Override
		public void controllerEvent(ControllerEvent event) {
			event.getData();
		}

	}

	private class KeyboardTestComponent extends Component implements
			KeyListener {
		private static final long serialVersionUID = -487707956679764973L;

		public KeyboardTestComponent() {
			super();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			cntrl.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			cntrl.keyReleased(e);
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// DO NOTHING
		}
	}

	@Before
	public void setup() {
		cntrl = new KeyboardController();
		tester = new TestControllerListener();
		component = new KeyboardTestComponent();

		cntrl.addControllerListener(tester);
	}

	@Test
	public void testSpecifiedTests() {
		FakeEvent.sendKeyPress(component, KeyEvent.VK_0);
	}

	@Test
	public void testKeyPressAndRelease() {
		FakeEvent.sendKeyPress(component, KeyEvent.VK_0);
	}
}
