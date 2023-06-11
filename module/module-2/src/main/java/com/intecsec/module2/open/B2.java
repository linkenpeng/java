package com.intecsec.module2.open;

import java.lang.reflect.Method;

public class B2 {

	public static void main(String[] args) {
		B1 b1 = new B1();
		b1.print();


		try {
			Class c = Class.forName("com.intecsec.module1.open.A1");
			System.out.println(c.getName());
			
			c = Class.forName("com.intecsec.module1.open.A2");
			Method m = c.getDeclaredMethods()[0];
			m.setAccessible(true);
			m.invoke(c.newInstance(), null);
			System.out.println(c.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
