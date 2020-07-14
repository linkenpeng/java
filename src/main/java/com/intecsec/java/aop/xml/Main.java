package com.intecsec.java.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("aop-xml.xml");
		
		ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) context.getBean("arithmeticCalculator");
		double add = arithmeticCalculator.add(3, 6);
		double minus = arithmeticCalculator.minus(3, 6);
		double divide = arithmeticCalculator.divide(3, 1);
		
		System.out.println("add: " + add);
		System.out.println("minus: " + minus);
		System.out.println("divide: " + divide);
	}	
}
