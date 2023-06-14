package com.intecsec.java.basic.jvm.stack;

public class JvmStackSOF {
	
	private int len = 1;
	public void f1()
	{
		len ++;
		System.out.println(len);
		f1(); 
	}

	public static void main(String[] args) {
		new JvmStackSOF().f1();
	}
}
