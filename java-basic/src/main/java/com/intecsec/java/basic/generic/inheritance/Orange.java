package com.intecsec.java.basic.generic.inheritance;

public class Orange extends Fruit {
	public Orange(int size)
	{
		super(size);
	}
	
	public void hello() {
		System.out.println("I am a pear, with size " + getSize());

	}
}
