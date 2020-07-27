package com.intecsec.java.basic.exception;

public class Exception8Description {
	static void f() throws NullPointerException {
		MyObj myObj = null;
		myObj.m1();
	}

	public static void main(String[] args) {
		try {
			f();
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
	}
}