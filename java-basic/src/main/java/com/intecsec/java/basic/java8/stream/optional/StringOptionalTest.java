package com.intecsec.java.basic.java8.stream.optional;

import java.util.Optional;

public class StringOptionalTest {

	public static void main(String[] args) {
		Optional<String> s1 = Optional.of(new String("abc"));
		String s2 = s1.get();
		System.out.println("s2: " + s2); //abc
		
		Optional<String> s3 = Optional.empty();
		String s4 = s3.orElse("def"); 
		System.out.println("s4: " + s4); //def
		
		
		Optional<String> s5 = Optional.of(new String("abc"));
		Optional<String> s6 = Optional.empty();
		String s7 = null;
		Optional<String> s8 = Optional.ofNullable(s7); 
		//s7不为Null，s8就是s7, 否则s8就为Optional.empty()
		
	}

}
