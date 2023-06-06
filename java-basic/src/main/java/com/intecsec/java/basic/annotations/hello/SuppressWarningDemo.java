package com.intecsec.java.basic.annotations.hello;

import java.util.Date;

public class SuppressWarningDemo {

	@SuppressWarnings("all")
	public static void main(String[] args) {
		int a = 5;
		
		Date d = new Date();
		System.out.println(d.getYear());	
	}

}
