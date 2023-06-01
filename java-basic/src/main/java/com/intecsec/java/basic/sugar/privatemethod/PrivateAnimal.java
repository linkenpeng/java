package com.intecsec.java.basic.sugar.privatemethod;

public interface PrivateAnimal {

	public default void run()
	{
		move();
		move2();
		System.out.println("I can run");
	}
	public default void fly()
	{
		move();
		System.out.println("I can fly");
	}
	private void move()
	{
		//非静态的私有方法
		System.out.println("I can move");
		System.out.println("I am growing");
	}
	
	public static void run2()
	{
		move2();
		System.out.println("I can run");
	}
	public static void fly2()
	{
		move2();
		System.out.println("I can fly");
	}
	private static void move2()
	{
		//静态的私有方法
		System.out.println("I can move");
		System.out.println("I am growing");
	}
	
	
	
	
}
