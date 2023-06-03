package com.intecsec.java.basic.generic;

public class ArrayUtilTest {

	public static void main(String[] args) {
		ArrayUtil obj = new ArrayUtil();
		
		Integer result1 = obj.<Integer>getMin(1,2,3);
		Integer result2 = obj.getMin(3,2,1);
		
		System.out.println(result1);
		System.out.println(result2);
		
		// error Person类没有实现Comparable接口
		// Person result3 = obj.<Person>getMin(new Person("Mike"), new Person("Tom"));
	}

}
