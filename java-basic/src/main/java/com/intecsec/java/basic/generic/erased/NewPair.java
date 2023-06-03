package com.intecsec.java.basic.generic.erased;
import java.io.Serializable;

public class NewPair {

	private Comparable first;
	private Comparable second;

	public NewPair(Comparable first, Comparable second) {
		this.first = first;
		this.second = second;
	}

	public Comparable getFirst() {
		return first;
	}

	public void setFirst(Comparable first) {
		this.first = first;
	}
}

