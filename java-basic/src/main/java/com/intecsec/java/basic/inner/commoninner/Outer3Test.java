package com.intecsec.java.basic.inner.commoninner;

public class Outer3Test {

	public static void main(String[] args) {
		Outer3 foo1 = new Outer3();
		foo1.f1();
		
		Outer3.Bird foo2 = foo1.new Bird();
		foo2.fly();
		System.out.println(foo2 == foo1.getBird());
		
		//foo1对象下，有2个内部类对象和它关联
		
		//不允许没有关联的单独的普通内部类对象
		// Outer3.Bird foo3 = foo1.new Outer3.Bird();
	}

}
