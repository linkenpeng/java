package com.intecsec.java.basic.java8.stream.reduce;

import java.util.Optional;
import java.util.stream.Stream;

public class Reduce {

	public static void main(String[] args) {
		
		Integer[] a = new Integer[] {2,4,6,8};
		
		Stream<Integer> s1 = Stream.of(a);
		Optional<Integer> sum = s1.reduce(Integer::sum);
		System.out.println(sum.get());
		
		Stream<Integer> s2 = Stream.of(a);
		Optional<Integer> product = s2.reduce((x,y)->x*y);
		System.out.println(product.get());
		
		Stream<Integer> s3 = Stream.of(a);
		Integer product3 = s3.reduce(1,(x,y)->x*y);
		System.out.println(product3);
		
		String[] b = new String[] {"abc","def","ghi"};
		Stream<String> s4 = Stream.of(b);
		String bigStr = s4.reduce("",(x,y)->x+y);
		System.out.println(bigStr);
		

	}

}
