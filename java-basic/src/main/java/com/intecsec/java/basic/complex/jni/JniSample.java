package com.intecsec.java.basic.complex.jni;

public class JniSample {
	public native int sum(int num1, int num2);

	/**
	 * 1. 编写Java代码，注意native函数
	 * 2. 产生头文件 javah -cp bin com.intecsec.java.basic.jni.JniSample //now get edu_ecnu_JniSample.h file
	 * 3.implements edu_ecnu_JniSample.h using edu_ecnu_JniSample.c
	 * 4.compile edu_ecnu_JniSample.c get dll
	 * 5.run JniSample
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.loadLibrary("JniSample");  //dll名字
		JniSample sample = new JniSample();
		int sum = sample.sum(5, 7);
		
		System.out.println("和: " + sum);
	
	}
}
