package com.intecsec.java.basic.generic.genericinterface;

public class IntegerCalculator 
   implements Calculator<Integer> {

	public Integer add(Integer operand1, Integer operand2) {
		return operand1 + operand2;
	}
	
	public static void main(String[] args)
	{
		IntegerCalculator c1 = new IntegerCalculator();
		System.out.println(c1.add(1,2));
		
		Calculator<Integer> c2 = new IntegerCalculator();
		System.out.println(c1.add(1,2));
	}
}
