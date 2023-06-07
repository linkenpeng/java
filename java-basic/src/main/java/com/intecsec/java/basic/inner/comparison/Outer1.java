package com.intecsec.java.basic.inner.comparison;


public class Outer1 {

	private String name = "abc";
	public static int no = 5;
	
	//匿名内部类可在成员变量声明
	Runnable r1 = new Runnable() {
		final static int a = 5;
		public void run(){
			System.out.println("hello " + name + no);
		}
	};	
	
	public void f1() 
	{
		//匿名内部类可在成员方法内声明		
		Runnable r2 = new Runnable() {
			//static int a = 5;
			public void run(){
				System.out.println("hello "); 
			}
		};	
	}
}
