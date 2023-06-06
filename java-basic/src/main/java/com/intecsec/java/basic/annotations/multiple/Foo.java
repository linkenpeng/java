package com.intecsec.java.basic.annotations.multiple;

public class Foo {
	@MultipleTest(a=1,b=1)
	public static void m1(int a, int b) {
		if(a+b<0)
		{
			throw new RuntimeException("Crash");
		}
	}

	@MultipleTest
	public static void m2(int a, int b) {
		//全部采用默认值
		if(a+b<0)
		{
			throw new RuntimeException("Broken");
		}
	}

	@MultipleTest(b=-2,a=1)
	public static void m3(int a, int b) {
		if(a+b<0)
		{
			throw new RuntimeException("Boom");
		}
	}
}
