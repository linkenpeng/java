package com.intecsec.java.basic.sugar.importstatic;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.System.out;

public class ImportStaticTest {
	public static void importMath()	{
		int a=3, b=4, c=0;
		c = (int) sqrt(pow(a,2)+pow(b,2));
		c = (int) Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
		out.println("c is " + c);
		System.out.println("c is " + c);		
	}

	public static void main(String[] args) {
		importMath();
	}
}


