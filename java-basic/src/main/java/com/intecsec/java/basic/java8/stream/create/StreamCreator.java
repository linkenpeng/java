package com.intecsec.java.basic.java8.stream.create;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

public class StreamCreator {

	public static void main(String[] args) throws IOException {

		// Collection子类产生stream
		Stream<String> a1 = new ArrayList<String>().stream();
		Stream<String> a2 = new HashSet<String>().stream();

		// 使用Arrays.stream 转化数组为stream
		Stream<String> b1 = Arrays.stream("a,b,c,d,e".split(","), 3, 5);

		// 数组产生stream
		Stream<Integer> c1 = Stream.of(new Integer[5]);
		Stream<String> c2 = Stream.of("a,b,c".split(","));
		Stream<String> c3 = Stream.of("a", "b", "c");
		
		//空流
		Stream<String> d1 = Stream.empty();
		
		//无限流,使用generate方法，根据Lambda表达式产生
		Stream<String> e1 = Stream.generate(()->"hello");
		Stream<Double> e2 = Stream.generate(Math::random);
		
		//无限流，使用iterate方法，第一个参数是种子
		//第二个是Lambda表达式
		Stream<BigInteger> e3 = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
		
		//Files的lines方法读取一个文件，产生每一行内容的Stream
		Stream<String> contents = Files.lines(Paths.get("/tmp/abc.txt"));
		
		//Pattern的splitAsStream方法，根据一个正则表达式，将内容分为一个字符串的Stream
		Stream<String> words = Pattern.compile(",").splitAsStream("a,b,c");
		
		//words.forEach(System.out::println);
	}

}





