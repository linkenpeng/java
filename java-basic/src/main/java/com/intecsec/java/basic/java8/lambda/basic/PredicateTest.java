package com.intecsec.java.basic.java8.lambda.basic;

import java.util.function.Predicate;

public class PredicateTest {

	public static void main(String[] args) {
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", "Mars", 
				"Jupiter", "Saturn", "Uranus", "Neptune" };
		
		StringChecker evenLength = s ->
				s.length() % 2 == 0 ? true : false;

			
		for(String p : planets)	{
			if(evenLength.test(p)) {
				System.out.println(p);
			}
		}

		System.out.println("=============");
		
		Predicate<String> oddLength = s -> 
			s.length() % 2 == 0 ? true : false;
			
		for(String p : planets)	{
			if(oddLength.test(p)) {
				System.out.println(p);
			}
		}
	}
}
