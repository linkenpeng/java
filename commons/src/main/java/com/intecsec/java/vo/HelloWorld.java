package com.intecsec.java.vo;

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
