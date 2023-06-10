package com.intecsec.java.basic.lambda.scope;

public class ObjectInstanceMethodTest {

	public static void worker(PrintFunction pf, String s) {
		pf.exec(s);
	}
	
	public static void main(String[] args) {
		String a = "abc";
		worker(System.out::println, a);
	}
}

interface PrintFunction {
	public void exec(String s);
}

