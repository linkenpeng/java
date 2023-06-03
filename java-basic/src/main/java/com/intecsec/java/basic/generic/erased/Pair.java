package com.intecsec.java.basic.generic.erased;

//Pait<T>经过类型擦除后的代码
public class Pair {
	
	private Object first;
	private Object second;

	public Pair(Object first, Object second) {
		this.first = first;
		this.second = second;
	}

	public Object getFirst() {
		return first;
	}

	public void setFirst(Object first) {
		this.first = first;
	}
}

