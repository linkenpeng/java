package com.intecsec.java.basic.refelect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author peter.peng
 *
 * 类：Class
 * 属性：Field
 * 方法：Method
 * 构造器：Constuctor
 *
 * @date 2019/5/17
 */
public class RefelectMain {

	public static void main(String[] args) throws ClassNotFoundException {
		testMethod();
	}

	public static void testClassForField() {
		Class clazz = RefelectTestA.class;

		// 全部申明的属性
		System.out.println("all fields");
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field);
			System.out.println(Modifier.toString(field.getModifiers()));
			System.out.println(field.getType().getSimpleName());
			System.out.println(field.getName());
		}

		System.out.println("public fields");

		// 只能获取public属性
		Field[] fields2 = clazz.getFields();
		for (int i = 0; i < fields2.length; i++) {
			System.out.println(fields2[i].getName());
		}
	}

	public static void testCreateClass() throws ClassNotFoundException {
		System.out.println(RefelectTestA.class);

		Class<?> clazz = Class.forName("com.mycshop.basic.refelect.RefelectTestA");
		System.out.println(clazz);

		RefelectTestA refelectTestA = new RefelectTestA();
		System.out.println(refelectTestA.getClass());
	}

	public static void testMethod() {
		Class<RefelectTestA> clazz = RefelectTestA.class;

		Method[] methods = clazz.getDeclaredMethods();

		for (Method method : methods) {
			System.out.println(method);
			System.out.println(method.getTypeParameters());
		}

	}

}
