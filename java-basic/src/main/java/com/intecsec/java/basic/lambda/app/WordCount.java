package com.intecsec.java.basic.lambda.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WordCount {

	public static void main(String[] args) {
		String s = "I like Java . "
				+ "Java is the best programming "
				+ "language in the world";
		String[] words = s.split(" ");
		
		HashMap<String, Integer> counter = new HashMap<>();
		
		for(String word:words)
		{
			//counter.merge(word, 1, (count, incr) -> count + incr);
			counter.merge(word, 1, Integer::sum);
		}
		
		for(String key : counter.keySet())
		{
			System.out.println(key + ", count " + counter.get(key));
		}
	}

}
