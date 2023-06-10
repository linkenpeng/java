package com.intecsec.java.basic.java8.stream.optional;

public class User {
	private int age;
	private String name;
	
	public User(String name, int age) {
		this.age = age;
		this.name = name;
	}
	
	public User()
	{
		this.age = 20;
		this.name = "noname";
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}	
	
	public String toString() {
		return name + "," + age;
	}
	
}
