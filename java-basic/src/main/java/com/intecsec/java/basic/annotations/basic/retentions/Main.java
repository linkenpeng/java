package com.intecsec.java.basic.annotations.basic.retentions;

public class Main {
	public static void main(String[] a) throws Exception {
		// RetentionPolicy.SOURCE
		//注解不会保存到class文件中
		Class a1 = Class.forName("retentions.A1");
		Class a2 = Class.forName("retentions.A2");
		System.out.println(a1.getAnnotations().length);
		System.out.println(a2.getAnnotations().length);
		System.out.println("=========================");

		// RetentionPolicy.CLASS
		// 注解会保存到class文件中，但是不会被JVM加载
		Class b1 = Class.forName("retentions.B1");
		Class b2 = Class.forName("retentions.B2");
		System.out.println(b1.getAnnotations().length);
		System.out.println(b2.getAnnotations().length);
		System.out.println("=========================");

		// RetentionPolicy.RUNTIME
		// 注解会保存在class文件中，且会被JVM加载，反射识别
		Class c1 = Class.forName("retentions.C1");
		Class c2 = Class.forName("retentions.C2");
		System.out.println(c1.getAnnotations().length);
		System.out.println(c2.getAnnotations().length);
		System.out.println("=========================");

	}
}
