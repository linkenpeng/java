package com.intecsec.java.basic.refelect.basic;

public class ClassTest {

	public static void main(String[] args) throws ClassNotFoundException {		
		
		String s1 = "abc";
		Class c1 = s1.getClass();
		System.out.println(c1.getName());
		
		Class c2 = Class.forName("java.lang.String");
		System.out.println(c2.getName());
		
		Class c3 = String.class;
		System.out.println(c3.getName());	

	}

}
