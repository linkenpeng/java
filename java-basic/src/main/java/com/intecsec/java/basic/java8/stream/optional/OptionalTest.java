package com.intecsec.java.basic.java8.stream.optional;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalTest {

	public static void main(String[] args) {
		
		Integer[] a = new Integer[] {2,4,6,8};		
		
		Stream<Integer> s1 = Stream.of(a);		
		long countResult = s1.count();
		System.out.println("the count result of s1 is " + countResult);
		
		Stream<Integer> s2 = Stream.of(a);
		countResult = s2.filter(n-> n>10).count();
		System.out.println("the count result of s2 is " + countResult);
		
		Stream<Integer> s3 = Stream.of(a);
		Optional<Integer> maxResult = s3.max((n1,n2)->n1-n2);
		System.out.println("the max result of s3 is " + maxResult.get());
		
		Stream<Integer> s4 = Stream.of(a);
		maxResult = s4.filter(n-> n>10).max((n1,n2)->n1-n2);
		System.out.println("the max result of s4 is " + maxResult.get());
		

	}

}
