package com.intecsec.java.basic.sugar.defaultmethod;

public interface NewAnimal {
	public default void move()
	{
		System.out.println("I can move.");
	}
	
	//不能重写Object的方法
//	public default String toString()
//	{
//		return "aaa";
//	}
}


