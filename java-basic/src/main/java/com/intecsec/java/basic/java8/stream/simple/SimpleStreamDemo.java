package com.intecsec.java.basic.java8.stream.simple;

import java.util.Arrays;
import java.util.List;

public class SimpleStreamDemo {

	public static void main(String[] args) {
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", 
				"Mars", "Jupiter", "Saturn", 
				"Uranus", "Neptune" };
		
		//查找星球名字大于等于5个字母
		long count = 0;
		for(String p : planets)	{
			if(p.length() > 5) {
				count ++;
			}
		}
		System.out.println(count);
		
		List<String> pList = Arrays.asList(planets);
		
		//采用流方法
		count = pList.stream()
				.filter(p->p.length()>5).count();
		System.out.println(count);
		
		//采用并行流方法
		count = pList.parallelStream()
				.filter(p->p.length()>5).count();
		System.out.println(count);
		

	}

}
