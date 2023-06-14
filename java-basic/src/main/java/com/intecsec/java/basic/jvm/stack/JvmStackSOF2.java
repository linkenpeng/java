package com.intecsec.java.basic.jvm.stack;

public class JvmStackSOF2 {
	
	private int len = 1;
	public void f2() {
		long a1,a2,a3,a4,a5,a6,a7,a8,a9,a10;
		a1=a2=a3=a4=a5=a6=a7=a8=a9=a10=0;
		len ++;
		System.out.println(len);
		f2(); 
	}

	public static void main(String[] args) {
		new JvmStackSOF2().f2();
	}
}
