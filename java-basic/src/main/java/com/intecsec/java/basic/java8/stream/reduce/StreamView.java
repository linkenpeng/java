package com.intecsec.java.basic.java8.stream.reduce;

import java.util.Iterator;
import java.util.stream.Stream;

public class StreamView {

	public static void main(String[] args) {
		Integer[] a = new Integer[] {2,4,6,8};
		
		Stream<Integer> s1 = Stream.of(a);
		Iterator<Integer> it1 = s1.filter(n->n>2).iterator();
		while(it1.hasNext()) {
			System.out.println(it1.next());
		}
		
		Stream<Integer> s2 = Stream.of(a);
		s2.filter(n->n>2).forEach(System.out::println);
		
		
	}

}
