package com.intecsec.java.basic.jvm.reference;

public class Test {

	public static void main(String[] args) {
		Test foo = new Test();
		String s2 = foo.f1();
	}
	
	public String f1()
	{
		String s1 = new String("123");
		return s1;
	}
}
