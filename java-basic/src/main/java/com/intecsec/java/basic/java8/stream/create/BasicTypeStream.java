package com.intecsec.java.basic.java8.stream.create;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BasicTypeStream {

	public static void main(String[] args) {
		
		IntStream s1 = IntStream.of(1,2,3,4,5);
		
		s1 = Arrays.stream(new int[] {1,2,3});		
		s1 = IntStream.generate(()->(int)(Math.random() * 100));		
		s1 = IntStream.range(1,5); //1,2,3,4  step 1		
		s1 = IntStream.rangeClosed(1,5); //1,2,3,4,5
		
		IntStream s2 = IntStream.of(1,2,3,4,5);
		Stream<Integer> s3 = s2.boxed();
		IntStream s5 = s3.mapToInt(Integer::intValue);
	}

}
