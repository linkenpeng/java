package com.intecsec.java.basic.generic.genericclass;

public class IntervalTest {

	public static void main(String[] args) {		
		Interval<Integer> v1 = new Interval<Integer>(1,2);
		int lower = v1.getLower();
		int upper = v1.getUpper();		
		System.out.println(lower + "," + upper);
		
		Interval<Integer> v2 = new Interval<>(1,2);		
		Interval<Integer> v3 = getReverse(v2);		
		System.out.println(v3.getLower() + "," + v3.getUpper());
	}
	
	public static <T> Interval<T> getReverse(Interval<T> interval){
		return new Interval<T>(interval.getUpper(), interval.getLower());
	}

}
