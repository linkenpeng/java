package com.intecsec.java.basic.java8.lambda.scope;

import java.util.Arrays;

public class SuperInstanceMethodTest extends Father{
	public static void main(String[] args) {
		SuperInstanceMethodTest obj = new SuperInstanceMethodTest();
		obj.test();
	}	
	
	public void test()	{
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", "Mars", 
				"Jupiter", "Saturn", "Uranus", "Neptune" };	
				
		Arrays.sort(planets, super::lengthCompare);
		System.out.println(Arrays.toString(planets));	
	}
}

class Father {
	public int lengthCompare(String first, String second){
		return first.length() - second.length();
	}
}

