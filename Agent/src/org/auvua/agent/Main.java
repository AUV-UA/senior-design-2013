package org.auvua.agent;

import java.util.Arrays;

import org.zeromq.*;
import org.auvua.protobuff.*;
import org.auvua.protobuff.AUVprotocol.AUV_State;

import com.google.protobuf.InvalidProtocolBufferException;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
		subscriber.connect("ipc:///tmp/CATFISH");
		subscriber.subscribe("AUV_State".getBytes());
		
		Thread.sleep(100);
		
		while(true) {
			byte[] input;
			String strin = "";
			
			
			do {
				strin = subscriber.recvStr();
			} while(!strin.equals("AUV_State"));
				
				//System.out.println(subscriber.recvStr() + "\n\n");
			try {
				input = subscriber.recv();
				AUV_State state = AUV_State.parseFrom(input);
				System.out.println(state.getTimestamp());
				if(state.hasTelemetry()) {
					System.out.println("Heading: " + state.getTelemetry().getHeading()
							+ "\tRoll: " + state.getTelemetry().getRoll());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Thread.sleep(10);
		}
	}
}
