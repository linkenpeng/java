package com.intecsec.java.basic.annotations.single;

public class Foo {
	@SingleTest(1)
	public static void m1(int a) {
		if(a<0)
		{
			throw new RuntimeException("Crash");
		}
	}

	public static void m2() {
	}

	@SingleTest(value=-2)
	public static void m3(int a) {
		if(a<0)
		{
			throw new RuntimeException("Crash");
		}
	}
}
