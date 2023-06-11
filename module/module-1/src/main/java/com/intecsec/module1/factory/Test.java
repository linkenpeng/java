package com.intecsec.module1.factory;


import com.intecsec.module1.example.Shoe;

public class Test {
	public static void main(String[] args) {
		Shoe obj = ShoeFactory.getShoe("Warrior");
		obj.walk();
	}
}


