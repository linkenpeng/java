package com.intecsec.java.basic.keyword;

/**
 * @author peter.peng
 * @date 2019/5/17
 */
public class StaticTest {
	private static String a;

	public static void setVar() {
		StaticTest.a = "a";
	}

	public static String getA() {
		return a;
	}
}
