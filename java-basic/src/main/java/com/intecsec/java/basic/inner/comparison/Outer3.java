package com.intecsec.java.basic.inner.comparison;

public class Outer3 {
	
	protected class Inner3 {
		public static final int a=3; //常量OK
		//static int b = 5;
		public void f1() {
			System.out.println("hello");
		}
	}
	
}
