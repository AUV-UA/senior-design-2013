package org.auvua.agent;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		Mission mission = new Mission("/home/catfish/workspace/test.xml");
		mission.execute();

	}
}
