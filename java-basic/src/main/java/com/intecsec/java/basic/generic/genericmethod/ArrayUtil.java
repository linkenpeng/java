package com.intecsec.java.basic.generic.genericmethod;

public class ArrayUtil {
	public static <T> T getMiddle(T... a)
	{
		return a[a.length/2];
	}
}

