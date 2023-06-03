package com.intecsec.java.basic.generic.array;

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
	
	public static void main(String[] a)
	{
		// Pair<String>[] stringPairs=new Pair<String>[10];
	}
}

