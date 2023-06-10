package com.intecsec.java.basic.java8.lambda.app;

import java.util.Arrays;
import java.util.List;

public class ListForEachTest {

	public static void main(String[] args) {
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", "Mars", 
				"Jupiter", "Saturn", "Uranus",
				"Neptune" };
		
		List<String> pList = Arrays.asList(planets);
		
		//从JDK5开始支持for-each语法，但遍历仍显笨拙
		for(String s : pList)
		{
			System.out.println(s);
		}
		
		//从JDK8开始支持Lamdba表达式，遍历更简洁
		pList.forEach(System.out::println);

	}

}
