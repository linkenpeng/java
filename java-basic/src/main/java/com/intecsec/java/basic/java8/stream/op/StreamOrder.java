package com.intecsec.java.basic.java8.stream.op;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class StreamOrder {
	public static void main(String[] args) {
		System.out.println("=======对基本类型包装类对象进行排序======");
		
		Stream<Integer> s1 = Stream.of(3,2,4,1,5);
		Stream<Integer> s2 = s1.sorted();
		s2.forEach(System.out::println);
		//1, 2, 3, 4, 5
		
		System.out.println("=======提供Comparator进行排序===============");
		
		
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", 
				"Mars", "Jupiter", "Saturn", 
				"Uranus", "Neptune" };
		
		Stream<String> s3 = Stream.of(planets).sorted(
				Comparator.comparing(String::length));
		s3.forEach(System.out::println);
		
		System.out.println("========对自定义对象进行排序==============");
		
		ArrayList<Cat> cats = new ArrayList<>();
		cats.add(new Cat(3));
		cats.add(new Cat(2));
		cats.add(new Cat(5));
		cats.add(new Cat(1));
		cats.add(new Cat(4));
		Stream<Cat> s4 = cats.stream().sorted();
		s4.forEach(System.out::println);
	}	
}

class Cat implements Comparable<Cat> {
	private int size;

	public Cat(int size) {
		super();
		this.size = size;
	}

	@Override
	public int compareTo(Cat o) {
		Cat c = new Cat(5);
		System.out.println(c.size);
		return this.size - o.size;	
	}
	
	public String toString()
	{
		return "Size:" + size;
	}
}
