package com.intecsec.java.basic.system;

import java.io.InputStream;

public class RuntimeTest {

	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		System.out.println(runtime.freeMemory());
		System.out.println(runtime.totalMemory());

		try {
			runtime.exec("notepad");
			Process process = runtime.exec("java ArrayTest");
			InputStream inputStream = process.getInputStream();
			int data;
			while ((data = inputStream.read()) != -1) {
				System.out.print((char) data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
