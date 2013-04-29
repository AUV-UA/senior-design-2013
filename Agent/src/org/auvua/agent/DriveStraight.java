package org.auvua.agent;

import java.util.ArrayList;

public class DriveStraight extends Task {

	public DriveStraight() {
		finished = false;
		num_params = 2;
	}
	
	@Override
	public void run() {
		System.out.println("Starting task...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Task done");
		finished = true;
	}
	
}
