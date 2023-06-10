package com.intecsec.java.basic.java8.lambda.app;

import java.util.function.Predicate;

public class PredicateTest {

	public static void main(String[] args) {
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", "Mars", 
				"Jupiter", "Saturn", "Uranus", "Neptune" };
		
		StringChecker evenLength = s -> 
			{
				if(s.length()%2 == 0)
					return true;
				return false;
			};
			
		for(String p : planets)	{
			if(evenLength.test(p)) {
				System.out.println(p);
			}
		}
		
		
		Predicate<String> oddLength = s -> 
			s.length()%2 == 0 ? false:true;
			
		for(String p : planets)	{
			if(oddLength.test(p)) {
				System.out.println(p);
			}
		}
	}
}
