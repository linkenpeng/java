package com.intecsec.java.basic.java8.stream;

import java.util.stream.IntStream;

public class OrderStream {

	public static void main(String[] args) {
		IntStream.iterate(0, i -> i + 1)
		.limit(10) // LIMIT
		.skip(5) // OFFSET
		.forEach(System.out::println); //5-9
		
		IntStream.iterate(0, i -> i + 1)		
		.skip(5) // OFFSET
		.limit(10) // LIMIT
		.forEach(System.out::println); //5-14

	}

}
