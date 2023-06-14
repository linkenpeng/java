package com.intecsec.java.basic.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * -Xmx5m
 */
public class SoftReferenceTest {

	public static void main(String[] args) {
		StringBuilder s1 = new StringBuilder();
		for(int i=0;i<100000;i++)
		{
			s1.append("0000000000");
		}
		
		SoftReference<StringBuilder> s2 = new SoftReference<StringBuilder>(s1);
		s1 = null;
		
		System.out.println(s2.get().length()); //not null
		
		System.gc();
		//软引用, 内存不紧张, 没有回收
		System.out.println(s2.get().length()); //not null
		
		byte[] b = new byte[(int)(1024*1024*3.5)];
		
		System.gc();
		//内存紧张, 软引用被回收
		System.out.println(s2.get()); //null
		
	}

}
