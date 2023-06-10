package com.intecsec.java.basic.java8.stream.op;

import java.util.stream.Stream;

public class StreamFilter {

	public static void main(String[] args) {
		System.out.println("======对每个元素进行过滤判定================");
		
		Stream<Integer> s1 = Stream.of(1, 2, 3, 4, 5);
		Stream<Integer> s2 = s1.filter(n -> n>2);
		s2.forEach(System.out::println);
		//3, 4, 5
	}
}
