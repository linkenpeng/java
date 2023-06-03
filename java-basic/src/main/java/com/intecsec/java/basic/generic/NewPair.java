package com.intecsec.java.basic.generic;

import java.io.Serializable;

public class NewPair<T extends Comparable & Serializable> {

	private T first;
	private T second;

	public NewPair(T first, T second) {
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

