package com.intecsec.java.basic.generic.genericinterface;

public class IntervalCalculator implements Calculator<Interval<Integer>>{

	public static void main(String[] args) {
		Calculator<Interval<Integer>> c = new IntervalCalculator();
		
		Interval<Integer> operand1 = new Interval<Integer>(1,2);
		Interval<Integer> operand2 = new Interval<Integer>(3,4);
		Interval<Integer> operand3 = c.add(operand1, operand2);
		System.out.println("[" + operand3.getLower() + "," + operand3.getUpper() + "]");
	}

	public Interval<Integer> add(Interval<Integer> operand1, Interval<Integer> operand2) {
		int lower = operand1.getLower() + operand2.getLower();
		int upper = operand1.getUpper() + operand2.getUpper();
		return new Interval<Integer>(lower, upper);
	}
}
