package com.intecsec.java.basic.java8.stream.op;

import java.util.stream.Stream;

public class StreamOther {

	public static void main(String[] args) {
		Stream<Double> s1 = Stream.iterate(1.0, n -> n*2)
				.peek(n -> System.out.println("number:" + n)).limit(5);
		s1.forEach(System.out::println);

	} 

}
