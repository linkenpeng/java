package com.intecsec.java.basic.generic.inheritance;

public abstract class Fruit {
	private int size;
	
	public int getSize()
	{
		return size;
	}
	
	public Fruit(int size)
	{
		this.size = size;
	}
	
	public abstract void hello();
	
}
