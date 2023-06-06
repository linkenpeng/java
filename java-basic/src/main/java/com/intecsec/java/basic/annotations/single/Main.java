package com.intecsec.java.basic.annotations.single;

import java.lang.reflect.*;

public class Main {
	public static void main(String[] args) throws Exception {
		int passed = 0, failed = 0;
		String className = "com.intecsec.java.basic.annotations.single.Foo";
		for (Method m : Class.forName(className).getMethods()) {
			
			if (m.isAnnotationPresent(SingleTest.class)) {
				System.out.println(m.getName());
				SingleTest st = m.getAnnotation(SingleTest.class);				
				try {
					m.invoke(null,st.value());
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
