package com.intecsec.java.basic.bytecode.premain;


import com.intecsec.java.basic.bytecode.test.Greeting;

public class Test {
	public static void main(String[] args) {
		Greeting foo = new Greeting();
		foo.hello();
	}
}
