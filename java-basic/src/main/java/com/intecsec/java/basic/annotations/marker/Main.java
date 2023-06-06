package com.intecsec.java.basic.annotations.marker;
import java.lang.reflect.*;

public class Main {
	public static void main(String[] args) throws Exception {
	      int passed = 0, failed = 0;
	      String className = "com.intecsec.java.basic.annotations.marker.Foo";
	      for (Method m : Class.forName(className).getMethods()) {
	         if (m.isAnnotationPresent(Test.class)) {
	            try {
	               m.invoke(null);
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
