package com.intecsec.java.basic.lambda.app;

public class ThisScopeTest {
	private String name = "def";

	public static void main(String[] args) {
		new ThisScopeTest().test();
	}
	
	public void test() {
		StringOperation obj = ()->
		{
			System.out.println(this.name);
			System.out.println(getName());
		};
		obj.operate();
	}
	public String getName() {
		return this.name;
	}	
}

interface StringOperation {
	String name = "abc";
	public void operate();
}
