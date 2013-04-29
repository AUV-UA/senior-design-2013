package org.auvua.agent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Mission implements Runnable {

	private final boolean DEBUG = true;
	private ArrayList<Task> tasks;
	private Thread worker;
	
	public Mission(String filename) {
		tasks = new ArrayList<Task>();
		File xmlFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document file = dBuilder.parse(xmlFile);
			file.getDocumentElement().normalize();
			
			NodeList nl = file.getElementsByTagName("task");
			
			for(int i = 0; i < nl.getLength(); i++) {
				Node n = nl.item(i);
				Element el = (Element) n;
				
				String taskName = el.getElementsByTagName("name").item(0).getTextContent();
				
				Location taskLoc = new Location();
				Node loc_node = el.getElementsByTagName("location").item(0);
				taskLoc.angle = Float.parseFloat(loc_node.getAttributes().item(0).getNodeValue());
				taskLoc.x = Float.parseFloat(loc_node.getAttributes().item(1).getNodeValue());
				taskLoc.y = Float.parseFloat(loc_node.getAttributes().item(2).getNodeValue());
				taskLoc.z = Float.parseFloat(loc_node.getAttributes().item(3).getNodeValue());
				
				ArrayList<Parameter> params = new ArrayList<Parameter>();
				
				NodeList nl_params = el.getElementsByTagName("parameter");
				for(int j = 0; j < nl_params.getLength(); j++) {
					Element param_el = (Element) nl_params.item(j);
					String type = param_el.getAttribute("type");
					String name = param_el.getAttribute("name");
					String data = param_el.getTextContent();
					params.add(new Parameter(type, name, data));
				}
				
				Class<?> taskClass = Class.forName("org.auvua.agent." + taskName);
				Task t = (Task) taskClass.newInstance();
				t.setStartLocation(taskLoc);
				t.setParameters(params);
						
				tasks.add(t);
				
				if(DEBUG) {
					System.out.println("Task name: " + taskName);
					System.out.println("\tLocation:\n\t\tx: " + taskLoc.x 
							+ "\ty: " + taskLoc.y 
							+ "\tz: " + taskLoc.z
							+ "\tangle: " + taskLoc.angle);
					for(int j = 0; j < params.size(); j++) {
						Parameter temp = params.get(j);
						System.out.println("\tParameter:");
						System.out.println("\t\tName: " + temp.getName());
						System.out.println("\t\tType: " + temp.getType());
						System.out.println("\t\tData: " + temp.getData());
					}
				}
			}
			
		} catch (ParserConfigurationException | SAXException | IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			//TODO: add logging error results here
			System.out.println("Malformed mission file.");
			e.printStackTrace();
		}
	}
	
	public void start() {
		worker = new Thread(this);
		worker.start();
	}
	
	public void stop() {
		worker.interrupt();	
	}
	
	public void run() {
		for(Task task : tasks) {
			
			System.out.println("Running a task");
			task.run();
			System.out.println("Finished a task");
			
			while(!task.isFinished()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
			}
			
			if(worker.isInterrupted()) {
				System.out.println("Interrupting cow!");
				return;
			}
		}
	}
	
}
