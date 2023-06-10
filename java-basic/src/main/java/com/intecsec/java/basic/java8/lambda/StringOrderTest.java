package com.intecsec.java.basic.java8.lambda;

import java.util.Arrays;

public class StringOrderTest {

	public static void main(String[] args) {
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", "Mars", 
				"Jupiter", "Saturn", "Uranus",
				"Neptune" };	

		System.out.println(Arrays.toString(planets));
		System.out.println("=======================");

		System.out.println("使用长度从小到大的比较器:");
		Arrays.sort(planets, new LengthAscComparator());
		System.out.println(Arrays.toString(planets));
		System.out.println("使用长度从大到小的比较器:");
		Arrays.sort(planets, new LengthDescComparator());
		System.out.println(Arrays.toString(planets));		

		System.out.println("=======================");
		System.out.println("使用Lambda, 长度从小到大:");
		Arrays.sort(planets, 
				(String first, String second) 
				   -> first.length() - second.length());
		System.out.println(Arrays.toString(planets));
		
		System.out.println("使用Lambda, 长度从大到小:");
		Arrays.sort(planets, (first, second) -> (-1) * (first.length() - second.length()));
		System.out.println(Arrays.toString(planets));
		
		System.out.println("使用Lambda, 长度从大到小:");
		Arrays.sort(planets, 
			(String first, String second) -> 
			{
				int result = (-1) * (first.length() - second.length());
				return result;
			}
		);
		System.out.println(Arrays.toString(planets));

	}

}
