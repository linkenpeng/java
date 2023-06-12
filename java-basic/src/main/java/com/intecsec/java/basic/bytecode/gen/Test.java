package com.intecsec.java.basic.bytecode.gen;

import com.intecsec.java.basic.bytecode.test.Greeting;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

	/**
	 * 在eclipse下，运行该类，使得Greeting.java被编译生成class文件
	 * 再运行Generator类，使得Greeting.class被修改，以及生成AsmHello.class
	 * 再运行该类可看到修改后效果
	 * 
	 * 由于修改、生成的class文件都位于target下，Project/Clean可清除所有class文件
	 */
	public static void main(String[] args) throws InterruptedException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		new Greeting().hello();
		
		System.out.println("====================");
		
		//运行过Generator类后会生成AsmHello.class，取消如下注释可看到效果
		Method newMethod = Class.forName("com.intecsec.java.basic.bytecode.test.AsmGreeting").getMethod("hello");
		newMethod.invoke(null);
	
	}

}
