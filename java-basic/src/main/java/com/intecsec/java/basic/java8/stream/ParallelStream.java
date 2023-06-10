package com.intecsec.java.basic.java8.stream;

import java.util.stream.IntStream;

public class ParallelStream {

	public static void main(String[] args) {
		
		IntStream s1 = IntStream.range(1,10000000);

		long t1 = System.nanoTime();
		long evenNum = s1.parallel().filter(n->n%2==0).count();
		long t2 = System.nanoTime();
		System.out.println(evenNum);
		System.out.println(t2 - t1);

		IntStream s2 = IntStream.range(1,10000000);
		t1 = System.nanoTime();
		evenNum = s2.filter(n->n%2==0).count();
		t2 = System.nanoTime();
		System.out.println(evenNum);
		System.out.println(t2 - t1);

	}

}
