package com.intecsec.java.basic.refelect.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static java.lang.System.out;

public class MethodTest {

	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		B obj = new B();
		Class c = obj.getClass();

		// 获取public方法 包括父类和父接口
		Method[] ms = c.getMethods();
		for (Method m : ms) {
			if ("f1".equals(m.getName())) {
				m.invoke(obj, null);
			}
		}

		// 获得该类的所有方法
		Method[] ms2 = c.getDeclaredMethods();
		for (Method m : ms2) {
			if ("f2".equals(m.getName())) {
				m.setAccessible(true);
				String result = (String) m.invoke(obj, "abc");
				out.println(result);
			}
		}
	}

}

class B {
	public void f1() {
		out.println("B.f1()...");
	}

	private String f2(String s) {
		out.println("B.f2()...");
		return s;
	}
}
