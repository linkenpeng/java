package com.intecsec.java.basic.inner.anonymousinner;

public class Outer1 {

	private String name = "abc";
	
	
	//匿名内部类
	//public static void f1() 
	public void f1() 
	{
		final String name = "def"; 
		
		Runnable r = new Runnable() {
			//匿名内部类不能定义静态变量，除非是常量
			public final static int a = 5;
			//public static int b = 3;
			//String name = "ghi";	
			public void run(){
				System.out.println("hello " + name); 
				//屏蔽外部方法的临时变量
				//https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html#shadowing
			}
			//静态方法不能在匿名内部类定义
//			public static void f2() {				
//			}
		};
		new Thread(r).start();
		System.out.println(r.getClass().getName());
		
		Runnable r2 = new Runnable() {
			public void run(){
				//System.out.println("hello " + this.name);
			}
		};
		new Thread(r2).start();
		System.out.println(r2.getClass().getName());	
		
	}
}
