package com.intecsec.java.basic.lambda.basic;

import java.util.Arrays;
import java.util.function.Supplier;

public class MethodReference {

	public static void main(String[] args) {
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", "Mars", 
				"Jupiter", "Saturn", "Uranus",
				"Neptune" };	

		System.out.println(Arrays.toString(planets));
		System.out.println("=======================");

		System.out.println("使用方法引用, 按照字母序排列:");
		Arrays.sort(planets, String::compareToIgnoreCase);
		System.out.println(Arrays.toString(planets));

		Supplier<String> s = ()-> new String("abc");
		System.out.println(s.get());
	}

}
