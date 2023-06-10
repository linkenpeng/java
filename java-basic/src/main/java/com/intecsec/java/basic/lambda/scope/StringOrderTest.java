package com.intecsec.java.basic.lambda.scope;

import java.util.Arrays;

public class StringOrderTest {

	public static void main(String[] args) {
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", "Mars", 
				"Jupiter", "Saturn", "Uranus",
				"Neptune" };	
				
		System.out.println(Arrays.toString(planets));
		System.out.println("=======================");

		System.out.println("使用方法引用对数组排序:");
		Arrays.sort(planets, String::compareToIgnoreCase);
		System.out.println(Arrays.toString(planets));		
	}
}
