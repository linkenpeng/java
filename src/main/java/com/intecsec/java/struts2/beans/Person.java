package com.intecsec.java.struts2.beans;

public class Person {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void hello() {
		System.out.println(this.username);
	}
}
