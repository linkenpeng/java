package com.intecsec.java.basic.jvm.reference;

/**
 * 赋值类的都是强引用
 * -Xmx5m
 */
public class StrongReferenceTest {

	public static void main(String[] args) {
		StringBuilder s1 = new StringBuilder();
		for(int i=0;i<10000;i++)
		{
			s1.append("00000000000000000000");
		}
		
		StringBuilder s2 = s1;
		s1 = null; //s1 为null, 但是s2依旧占据内存
		//s2 = null;
		System.gc(); 
		//垃圾回收, 无法对强类型引用回收, 内存被占用, 引发异常
		
		byte[] b = new byte[1024*1024*3];
		
	}

}
