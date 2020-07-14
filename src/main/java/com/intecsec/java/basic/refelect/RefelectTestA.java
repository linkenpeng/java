package com.intecsec.java.basic.refelect;

import lombok.Data;

/**
 * @author peter.peng
 * @date 2019/5/17
 */
public class RefelectTestA extends RefelectTestBase {
	public Integer i;

	protected Long l;
	private String str;
	static Double d;

	private long add(long a , long b) {
		return a + b;
	}

	public String str(String str1) {
		return str + str1;
	}

	protected double aDouble(double mod) {
		return mod / 3;
	}
}
