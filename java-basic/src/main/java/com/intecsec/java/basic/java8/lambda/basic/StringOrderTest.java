package com.intecsec.java.basic.java8.lambda.basic;

import java.util.Arrays;
import java.util.Comparator;

public class StringOrderTest {

	public static void main(String[] args) {
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", "Mars", 
				"Jupiter", "Saturn", "Uranus",
				"Neptune" };	

		System.out.println(Arrays.toString(planets));
		System.out.println("=======================");

		System.out.println("使用Lambda, 长度从小到大:");
		Arrays.sort(planets, 
				(String first, String second) 
				   -> first.length() - second.length());
		System.out.println(Arrays.toString(planets));
		
		System.out.println("使用Lambda, 长度从大到小:");
		Arrays.sort(planets, 
			(first, second) ->       
			{
				//形参不写类型  可以从上下文推断出来
				int result = (-1) * (first.length() - second.length());
				return result;
			}
		);
		System.out.println(Arrays.toString(planets));
		
		Comparator<String> c = new Comparator<String>()	{
					public int compare(String first, String second)     
					{
						return first.length() - second.length();
					}
				};
		
		Comparator<String> c2 = (String first, String second) ->       
			first.length() - second.length();		
			
			
		Arrays.sort(planets, c2);
		
		

	}

}
