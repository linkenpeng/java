package com.intecsec.java.basic.exception;

public class Exception2NullPointer {

	public static void main(String[] args) {
		MyObj myObj = null;
		
		try {
			myObj.m1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class MyObj {
	public void m1() {
		System.out.println("MyObj m1().");
	}
}