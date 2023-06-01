package com.intecsec.java.basic.sugar.staticmethod;

public class StaticSwan implements StaticAnimal {
	
	public static void main(String[] args) {
		StaticAnimal.move();
		// StaticLandAnimal.move(); //error
		// new StaticSwan().move(); //error
	}
}



//	public static void move()
//	{
//		System.out.println("I can fly");
//	}


