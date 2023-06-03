package com.intecsec.java.basic.generic.genericclass;

public class IntegerInterval {
	private int lower;
	private int upper;

	public IntegerInterval(int lower, int upper) {
		this.lower = lower;
		this.upper = upper;
	}

	public int getLower() {
		return lower;
	}

	//部分get/set方法省略
}
