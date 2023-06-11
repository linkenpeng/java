package com.intecsec.module1.first.p1;


import com.intecsec.module1.first.p2.DoubleStar;

public class ShoeFactory {
	public static Shoe provider() {
		Shoe result = new DoubleStar();
		return result;
	}
}

