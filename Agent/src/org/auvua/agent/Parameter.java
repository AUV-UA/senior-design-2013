package org.auvua.agent;

public class Parameter {
	
	private String type;
	private String name;
	private String data;
	
	public Parameter(String type, String name, String data) {
		this.type = type;
		this.name = name;
		this.data = data;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public Object getData() {
		Object res = null;
		
		if(type.equals("int")) {
			res = Integer.parseInt(data);
		} else if(type.equals("float")) {
			res = Float.parseFloat(data);
		} else if(type.equals("string")) {
			res = data;
		}
		
		return res;
	}
	
}
