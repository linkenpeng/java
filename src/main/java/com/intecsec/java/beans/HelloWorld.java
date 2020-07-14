package com.intecsec.java.beans;

public class HelloWorld {
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void sayHello() {
		System.out.println("Hello, " + name);
	}
}
