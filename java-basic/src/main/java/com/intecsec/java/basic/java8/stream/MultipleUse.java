package com.intecsec.java.basic.java8.stream;

import java.util.Optional;
import java.util.stream.Stream;

public class MultipleUse {

	public static void main(String[] args) {
		
		Integer[] a = new Integer[] {2,4,6,8};
		
		Stream<Integer> s1 = Stream.of(a);
		Stream<Integer> s2 = s1.filter(x->x>4);		
		//Stream<Integer> s3 = s1.filter(x->x>2);  //error
		
		//s1.forEach(System.out::println);         //error
		s2.forEach(System.out::println);
		//s2.forEach(System.out::println);           //error
		

	}

}
