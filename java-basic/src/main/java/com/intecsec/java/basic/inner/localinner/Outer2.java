package com.intecsec.java.basic.inner.localinner;

public class Outer2 {
	private String name = "abc";

	// 局部内部类
	public void f1() {
		String name = "def";

		class Inner2 {
			final static int a = 1;
			
			String name = "ghi";
			
			public Inner2() {}
			
			public void f2() {
				System.out.println(name);
				System.out.println(Outer2.this.name);
			}
		}

		Inner2 obj1 = new Inner2();
		obj1.f2();
		System.out.println(obj1.getClass().getName());
	}

	// 局部内部类
	public static void f2() {
		final String name = "def";

		class Inner2 {
			public void f2() {
				System.out.println(name);
				//System.out.println(Outer2.this.name);
			}
		}

		Inner2 obj1 = new Inner2();
		obj1.f2();
		System.out.println(obj1.getClass().getName());
	}
}
