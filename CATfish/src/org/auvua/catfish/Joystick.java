package org.auvua.catfish;

import net.java.games.input.*;

/**
 * Custom joystick implementation utilizing the JInput library. Coupled with JoystickEvent
 * and JoystickEventListener, this class connects to a specified Controller and starts a
 * thread that constantly polls the controller for changes. Any differences between polling 
 * iterations generate JoystickEvents.
 * 
 * @author forbesk
 */
public class Joystick implements Runnable {
	private Thread worker;
	private Controller controller;
	private int refresh_rate;
	private JoystickEventListener listener;
	private Component comps[];
	private double prev_vals[];
	
	public Joystick(Controller controller) {
		this(controller, 20);
	}
	
	public Joystick(Controller controller, int refresh_rate) {
		this.controller = controller;
		this.refresh_rate = refresh_rate;
		comps = controller.getComponents();
		prev_vals = new double[comps.length];
		
		worker = new Thread(this);
		worker.start();
	}

	@Override
	public void run() {
		//TODO: does this work if you close the thread?
		while(true) {
			controller.poll();		//poll controller for data
			
			double polldata;
			for(int i = 0; i < comps.length; i++) {
				polldata = comps[i].getPollData();
				if(polldata != prev_vals[i])
					sendJoystickEvent(comps[i]);	//send events on any changes
				prev_vals[i] = polldata;			//update for next iteration
			}
			try {
				Thread.sleep(refresh_rate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
	}
	
	/**
	 * Connects a JoystickEventListener to this Joystick.
	 * @param listener		JoystickEventListener to connect.
	 */
	public synchronized void setJoystickListener(JoystickEventListener listener) {
		this.listener = listener;
	}

	/**
	 * Sends a JoystickEvent
	 * @param comp		Component that fired the event.
	 */
	public void sendJoystickEvent(Component comp) {
		JoystickEvent event = new JoystickEvent(this, comp);
		listener.joystickEvent(event);
	}
}
