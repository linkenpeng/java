package com.intecsec.java.basic.sugar.defaultmethod;

public interface NewLandAnimal extends NewAnimal {
	public default void move()
	{
		System.out.println("I can move on land.");
	}
}
