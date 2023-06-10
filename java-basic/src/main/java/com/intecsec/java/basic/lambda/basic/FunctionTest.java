package com.intecsec.java.basic.lambda.basic;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import static java.lang.Math.*;

public class FunctionTest {

	public static void main(String[] args) {
		String[] planets = new String[] { 
				"Mercury", "Venus", "Earth", "Mars", 
				"Jupiter", "Saturn", "Uranus", "Neptune" };		
		
		
		//Function 有一个apply的抽象方法
		//接收一个参数，返回一个结果
		Function<String, String> upper = s -> 
			{
				//可以做更复杂的操作
				return s.toUpperCase();
			};
							
		for(String p : planets)	{
			System.out.println(upper.apply(p));
		}
	}
}
