package org.auvua.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TimerTask;
import java.util.logging.Logger;

import org.auvua.utils.protobuffer.AUVprotocol.AUVCommand;
import org.auvua.utils.protobuffer.AUVprotocol.AUVState;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import com.google.protobuf.InvalidProtocolBufferException;

public class AUVDispatch {
	private static AUVPublisher publisher;
	private static HashMap<String, AUVSubscriber> subscribers = null;
	private static String address;

	/* Global Java logger reference */
	public static final Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public AUVDispatch(String address) {
		subscribers = new HashMap<String, AUVDispatch.AUVSubscriber>();
		publisher = new AUVPublisher(address);
		this.address = address;
	}

	public static void sendCommand(AUVCommand command, String tag) {
		ByteArrayOutputStream data = new ByteArrayOutputStream();

		try {
			command.writeTo(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		publisher.publishTo(tag, data.toByteArray());
	}

	public static void sendState(AUVState state, String tag) {
		ByteArrayOutputStream data = new ByteArrayOutputStream();

		try {
			state.writeTo(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		publisher.publishTo(tag, data.toByteArray());
	}

	public static void subscribe(AUVCommandEventListener listener, String tag) {
		if (subscribers.containsKey(tag)) {
			AUVSubscriber sub = subscribers.get(tag);
			sub.addAUVCommandEventListener(listener);
		} else {
			AUVSubscriber sub = new AUVSubscriber(address, tag);
			sub.addAUVCommandEventListener(listener);
			subscribers.put(tag, sub);
		}
	}

	public static void unsubscribe(AUVCommandEventListener listener, String tag) {
		if (subscribers.containsKey(tag)) {
			AUVSubscriber sub = subscribers.get(tag);
			sub.removeAUVCommandEventListener(listener);
		}
	}

	public static void subscribe(AUVStateEventListener listener, String tag) {
		if (subscribers.containsKey(tag)) {
			AUVSubscriber sub = subscribers.get(tag);
			sub.addAUVStateEventListener(listener);
		} else {
			AUVSubscriber sub = new AUVSubscriber(address, tag);
			sub.addAUVStateEventListener(listener);
			subscribers.put(tag, sub);
		}
	}

	// TODO: This is blocking
	public static void updateSubscribers() {
		for (Entry<String, AUVSubscriber> entry : subscribers.entrySet()) {
			AUVSubscriber sub = entry.getValue();
			sub.update();
		}
	}

	public static void unsubscribe(AUVStateEventListener listener, String tag) {
		if (subscribers.containsKey(tag)) {
			AUVSubscriber sub = subscribers.get(tag);
			sub.removeAUVStateEventListener(listener);
		}
	}

	private static class AUVSubscriber {
		private final Socket socket;
		private ArrayList<AUVStateEventListener> state_listener;
		private ArrayList<AUVCommandEventListener> command_listener;

		public AUVSubscriber(String address, String tag) {
			final Context context = ZMQ.context(1);
			socket = context.socket(ZMQ.SUB);
			socket.bind(address);
			socket.subscribe(tag.getBytes());
		}

		private AUVState deserializeState(byte[] data) {
			AUVState state = null;
			try {
				state = AUVState.parseFrom(data);
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}

			return state;
		}

		private AUVCommand deserializeCommand(byte[] data) {
			AUVCommand command = null;

			try {
				command = AUVCommand.parseFrom(data);
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}

			return command;
		}

		// TODO: There must be a better way to do this.
		public void update() {
			byte[] data = null;

			String object = "";
			do {
				object = socket.recvStr();
			} while (object != "AUVState" && object != "AUVCommand");

			data = socket.recv();

			if (object == "AUVState" && data != null) {
				sendAUVStateEvent(deserializeState(data));
			}

			if (object == "AUVCommand") {
				sendAUVCommandEvent(deserializeCommand(data));
			}
		}

		public void addAUVStateEventListener(AUVStateEventListener listener) {
			state_listener.add(listener);
		}

		public void removeAUVStateEventListener(AUVStateEventListener listener) {
			state_listener.remove(listener);
		}

		public void addAUVCommandEventListener(AUVCommandEventListener listener) {
			command_listener.add(listener);
		}

		public void removeAUVCommandEventListener(
				AUVCommandEventListener listener) {
			command_listener.remove(listener);
		}

		public void sendAUVCommandEvent(AUVCommand command) {
			for (AUVCommandEventListener listener : command_listener) {
				listener.onAUVCommandEvent(new AUVCommandEvent(this, command));
			}
		}

		public void sendAUVStateEvent(AUVState state) {
			for (AUVStateEventListener listener : state_listener) {
				listener.onAUVStateEvent(new AUVStateEvent(this, state));
			}
		}
	}

	private static class AUVPublisher {
		private final Socket socket;

		public AUVPublisher(String address) {
			final Context context = ZMQ.context(1);
			socket = context.socket(ZMQ.PUB);
			socket.bind(address);
		}

		public void publishTo(String location, byte[] data) {
			socket.sendMore(location);
			socket.send(data, 0);
		}

	}
}
