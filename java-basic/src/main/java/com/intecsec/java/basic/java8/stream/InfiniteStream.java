package com.intecsec.java.basic.java8.stream;

import java.util.stream.IntStream;

public class InfiniteStream {

	public static void main(String[] args) {
		//无限流
		IntStream.iterate(0, i -> i + 1)
			.forEach(System.out::println);
		
		//需要对流元素进行限制
		IntStream.iterate(0, i -> i + 1)
		.limit(10).forEach(System.out::println);
		
		//又一个无限流
		IntStream.iterate(0, i -> ( i + 1 ) % 2)
		.distinct().limit(10).forEach(System.out::println);

	}

}
