package com.intecsec.java.basic.java8.stream.op;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamMap {

	public static void main(String[] args) {
		System.out.println("======用方法引用对每个元素进行计算=====");
		
		//map使用方法引用，输入一个参数，返回一个结果
		Stream<Double> s1 = Stream.of(-1.5, 2.5, -3.5);
		Stream<Double> s2 = s1.map(Math::abs);
		s2.forEach(System.out::println);
		
		System.out.println("======用Lambda表达式对每个元素进行计算=");
		
		//map使用Lambda表达式，输入一个参数，返回一个结果
		Stream<Integer> s3 = Stream.of(1,2,3,4,5);
		Stream<Integer> s4 = s3.map(n->n*n);
		s4.forEach(System.out::println);
		
		System.out.println("======对每个元素进行计算，返回Stream==");
		
		//map使用方法引用，输入一个参数，返回一个Stream
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth"};
		
		Stream<String> allLetters2 = 
				Stream.of(planets).flatMap(word -> letters(word));
		allLetters2.forEach(System.out::print);
		//flatMap 执行一对多的转换，然后将所有的Map都展开
		//['M','e','r','c','u','r','y',
		// 'V','e','n','u','s',
		// 'E','a','r','t','h']
		
		Stream<Stream<String>> allLetters = 
				Stream.of(planets).map(word -> letters(word));
		allLetters.forEach(System.out::print);
		//[['M','e','r','c','u','r','y'],
		// ['V','e','n','u','s'],
		// ['E','a','r','t','h']]
		
		System.out.println("======对每个元素进行计算，最后综合返回经过合并的Stream==");
		
		
		
	}
	
	public static Stream<String> letters(String word) {
		List<String> result = new ArrayList<>();
		for(int i=0;i<word.length();i++)
		{
			result.add(word.substring(i, i+1));
		}
		return result.stream();
	}
}


