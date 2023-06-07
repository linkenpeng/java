package com.intecsec.java.basic.inner.comparison;

public class Outer2 {
	private String name = "abc";
	public static int no = 5;
	
	// 局部内部类必须定义在成员方法内
	public void f1() {
		class Inner2 {
			//static int a = 5;
			final static int b = 5;
			
			public Inner2() {				
			}
			
			public void f2() {
				System.out.println("hello " + name + no);
			}
		}

		Inner2 obj1 = new Inner2();
		obj1.f2();
		System.out.println(obj1.getClass().getName());
	}	
}
