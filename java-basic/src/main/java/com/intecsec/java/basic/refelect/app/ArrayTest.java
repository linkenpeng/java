package com.intecsec.java.basic.refelect.app;

import java.lang.reflect.Array;

public class ArrayTest {

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		a = (int[]) goodCopy(a, 10);
		for (int i : a) {
			System.out.println(i);
		}
	}

	public static Object goodCopy(Object oldArray, int newLength) {
		// Array类型
		Class c = oldArray.getClass();

		// 获取数组中的单个元素类型
		Class componentType = c.getComponentType();

		// 旧数组长度
		int oldLength = Array.getLength(oldArray);

		// 新数组
		Object newArray = Array.newInstance(componentType, newLength);

		// 拷贝旧数据
		System.arraycopy(oldArray, 0, newArray, 0, oldLength);
		return newArray;
	}

}
