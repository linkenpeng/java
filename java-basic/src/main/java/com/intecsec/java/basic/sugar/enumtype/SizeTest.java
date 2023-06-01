package com.intecsec.java.basic.sugar.enumtype;


public class SizeTest {

	public static void main(String[] args) {
		
		Size s1 = Size.SMALL;
		Size s2 = Size.SMALL;
		Size s3 = Size.LARGE;
		
		System.out.println(s1 == s2); //true
		System.out.println(s1 == s3); //false
	}
}


enum Size {
	SMALL,MEDIUM,LARGE,EXTRA_LARGE;
}

