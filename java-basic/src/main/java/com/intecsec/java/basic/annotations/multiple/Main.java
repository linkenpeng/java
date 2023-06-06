package com.intecsec.java.basic.annotations.multiple;

import java.lang.reflect.*;

public class Main {
	public static void main(String[] args) throws Exception {
		int passed = 0, failed = 0;
		String className = "com.intecsec.java.basic.annotations.multiple.Foo";
		for (Method m : Class.forName(className).getMethods()) {
			
			if (m.isAnnotationPresent(MultipleTest.class)) {
				System.out.println(m.getName());
				MultipleTest st = m.getAnnotation(MultipleTest.class);				
				try {
					m.invoke(null,st.a(),st.b());
					passed++;
				} catch (Throwable ex) {
					System.out.printf("Test %s failed: %s %n", m, ex.getCause());
					failed++;
				}
			}
		}
		System.out.printf("Passed: %d, Failed %d%n", passed, failed);
	}
}
