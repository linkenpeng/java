package com.intecsec.java.basic.exception;

public class Exception1First {
	public static void main(String[] args) {
		try {
			throw new Exception("exp1");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			System.out.println("finally");
		}
	}
}
