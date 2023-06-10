package com.intecsec.java.basic.java8.stream.reduce;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class SimpleAggregation {

	public static void main(String[] args) {
		
		Integer[] a = new Integer[] {2,4,6,8};		
		
		Stream<Integer> s1 = Stream.of(a);
		long countResult = s1.filter(n-> n>0).count();
		System.out.println("the count result of s1 is " + countResult);
		
		Stream<Integer> s2 = Stream.of(a);
		Optional<Integer> maxResult = s2.max((n1,n2)->n1-n2);
		System.out.println("the max result of s2 is " + maxResult.get());
		
		Stream<Integer> s3 = Stream.of(a);
		Optional<Integer> minResult = s3.min((n1,n2)->n1-n2);
		System.out.println("the min result of s3 is " + minResult.get());
		
		
		Stream<Integer> s4 = Stream.of(a);
		Optional<Integer> first = s4.findFirst();
		System.out.println("the first result of s4 is " + first.get());
		
		Stream<Integer> s5 = Stream.of(a);
		Optional<Integer> random = s5.findAny();
		System.out.println("the random result of s5 is " + random.get());
		
		Stream<Integer> s6 = Stream.of(a);
		System.out.println(s6.anyMatch(n -> n>6));
		Stream<Integer> s7 = Stream.of(a);
		System.out.println(s7.allMatch(n -> n>6));
		Stream<Integer> s8 = Stream.of(a);
		System.out.println(s8.noneMatch(n->n>100));

	}

}
