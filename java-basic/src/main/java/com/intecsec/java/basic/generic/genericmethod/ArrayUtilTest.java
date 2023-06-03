package com.intecsec.java.basic.generic.genericmethod;
public class ArrayUtilTest {

	public static void main(String[] args) {
		String s1 = ArrayUtil.<String>getMiddle("abc", "def", "ghi");
		Integer i1 = ArrayUtil.getMiddle(1,2,3);
		
		//null is ok
		String s2 = ArrayUtil.<String>getMiddle("abc", "def", null);
		
		//error 寻找共同超类，再转型
		// Integer i2 = ArrayUtil.getMiddle(1,2.5f,3L);

	}

}
