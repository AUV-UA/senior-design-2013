package org.auvua.catfish.test;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.auvua.catfish.ControlType;
import org.auvua.catfish.Controller;
import org.auvua.catfish.ControllerEvent;
import org.auvua.catfish.ControllerEventListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

	private TestController cntrl;
	private TestListener tester;

	private class TestController extends Controller {
		final static String data = "TestData";

		public void fireEvent(ControlType type) {
			this.sendControllerEvent(type, data);
		}
	}

	private class TestListener implements ControllerEventListener {
		public ControlType recieved_type = null;

		@Override
		public void controllerEvent(ControllerEvent event) {
			recieved_type = event.getControlType();
		}
	}

	@Before
	public void setup() {
		cntrl = new TestController();
		tester = new TestListener();

		cntrl.addControllerListener(tester);
	}

	@After
	public void cleanup() {
		cntrl = null;
	}

	@Test
	public void test() {
		ControlType type = ControlType.Movement;
		cntrl.fireEvent(type);

		assertEquals(type, tester.recieved_type);
	}
}
