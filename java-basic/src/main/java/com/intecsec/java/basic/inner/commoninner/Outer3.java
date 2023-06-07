package com.intecsec.java.basic.inner.commoninner;

public class Outer3 {
	String name = "aaaaaa";
	
	public class Bird extends Animal implements Flyable {
		public static final int a=3; //常量OK
		//public static int b = 4;   //error
		public void fly() {
			System.out.println("I can fly " + name);
		}

		public void eat() {
			System.out.println("I can fly");
		}
	}
	public Bird obj = new Bird();
	public void f1() {
		obj.fly();
		System.out.println(obj.getClass().getName());
		this.name = "bbbbbb";
		obj.fly();
	}
	
	public Bird getBird()
	{
		return this.obj;
	}
}
