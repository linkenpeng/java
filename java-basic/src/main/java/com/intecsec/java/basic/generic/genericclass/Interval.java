package com.intecsec.java.basic.generic.genericclass;

public class Interval<T> {

	private T lower;
	private T upper;

	public Interval(T lower, T upper) {
		this.lower = lower;
		this.upper = upper;
	}

	public T getLower() {
		return lower;
	}

	public void setLower(T lower) {
		this.lower = lower;
	}

	public T getUpper() {
		return upper;
	}

	public void setUpper(T upper) {
		this.upper = upper;
	}
}
