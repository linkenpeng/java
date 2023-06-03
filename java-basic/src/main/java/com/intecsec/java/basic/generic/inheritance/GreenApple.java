package com.intecsec.java.basic.generic.inheritance;

public class GreenApple extends Apple{
	public GreenApple(int size)
	{
		super(size);
	}
	
	public void hello() {
		System.out.println("I am a green apple, with size " + getSize());
	}
	
	
}
