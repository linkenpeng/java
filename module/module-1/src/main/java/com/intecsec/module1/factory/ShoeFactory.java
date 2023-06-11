package com.intecsec.module1.factory;


import com.intecsec.module1.example.DoubleStar;
import com.intecsec.module1.example.Shoe;
import com.intecsec.module1.example.Warrior;

public class ShoeFactory {
	public static Shoe getShoe(String name) {
		Shoe result = null;
		if ("DoubleStar".equals(name)) {
			result = new DoubleStar();
		} else {
			result = new Warrior();
		}
		return result;
	}
}

