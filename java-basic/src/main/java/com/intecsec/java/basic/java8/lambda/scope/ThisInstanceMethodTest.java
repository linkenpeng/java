package com.intecsec.java.basic.java8.lambda.scope;

import java.util.Arrays;

public class ThisInstanceMethodTest {
	public static void main(String[] args) {
		ThisInstanceMethodTest obj = new ThisInstanceMethodTest();
		obj.test();
	}	
	
	public void test()	{
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", "Mars", 
				"Jupiter", "Saturn", "Uranus", "Neptune" };	
				
		Arrays.sort(planets, this::lengthCompare);
		System.out.println(Arrays.toString(planets));	
	}
	
	public int lengthCompare(String first, String second){
		return first.length() - second.length();
	}
}


