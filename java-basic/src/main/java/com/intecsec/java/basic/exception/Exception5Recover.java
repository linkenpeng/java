package com.intecsec.java.basic.exception;

public class Exception5Recover {
	static int i = 0;
	public static void increment() {
		i++;
	}
	
	public static void main(String[] args) {
		while (i < 10) {
			try {
				throw new Exception("Myexp");
			} catch (Exception e) {
				System.out.println("recover try" + i);
				increment();
			}
		}
	}
}