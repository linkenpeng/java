package com.intecsec.java.basic.java8.stream.op;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamSelection {

	public static void main(String[] args) {
		System.out.println("======取前几个元素================");
		//获取前n个元素
		Stream<Integer> s1 = Stream.of(1,2,3,4,5,6,7,8,9,10);
		Stream<Integer> s2 = s1.limit(3);
		s2.forEach(System.out::println);
		
		System.out.println("======跳过几个元素================");
		
		//一个流只能使用一次
		//跳过前n个元素
		Stream<Integer> s3 = Stream.of(1,2,3,4,5,6,7,8,9,10);
		Stream<Integer> s4 = s3.skip(8);
		s4.forEach(System.out::println);
		
		System.out.println("======连接两个流================");
		
		//连接两个流
		Stream<String> s5 = Stream.concat(letters("hello"), letters("world"));
		s5.forEach(System.out::println);
	}
	
	public static Stream<String> letters(String word)
	{
		List<String> result = new ArrayList<>();
		for(int i=0;i<word.length();i++)
		{
			result.add(word.substring(i, i+1));
		}
		return result.stream();
	}

}
