package com.intecsec.java.basic.refelect.app;

import com.google.gson.Gson;

public class JsonToObject {

	public static void main(String[] args) {
		Gson gson = new Gson();
		String s =  "{\"name\":\"Jo\""
				+ ",\"email\":\"a@b.com\"}";
		Person p = gson.fromJson(s, Person.class);
		
		System.out.println(p.getName());
		System.out.println(p.getEmail());
	}

}

class Person
{
	private String name;
	private String email;
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
}
