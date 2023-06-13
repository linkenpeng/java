package com.intecsec.java.basic.bytecode.confuse;

/**
 * Tools:
 * ProGuard https://www.guardsquare.com/en/products/proguard
 */
public class HelloWorld {

	public void say() {
		say2();
	}

	public void say2() {
		String name = "hello";
		String name2 = " world";
		System.out.println(name + name2);
	}
}


