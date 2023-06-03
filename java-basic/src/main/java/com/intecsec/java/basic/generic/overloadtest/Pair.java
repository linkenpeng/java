package com.intecsec.java.basic.generic.overloadtest;

public class Pair<T> {

	private T first;
	private T second;

	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}
}

