package com.intecsec.java.basic.java8.stream.optional;

public class NullTest {

	public static void main(String[] args) {
		String a = null;
		System.out.println(a.length());
		
		if(a != null)
		{
			System.out.println(a.length());
		}
	}
}
