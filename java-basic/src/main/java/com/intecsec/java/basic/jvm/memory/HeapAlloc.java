package com.intecsec.java.basic.jvm.memory;

/**
 * 来自于《实战Java虚拟机》
 * -Xms5M -Xmx20M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
 *
 */
public class HeapAlloc {

	public static void main(String[] args) {

		printMemoryInfo();
		byte[] b = new byte[1*1024*1024];
		System.out.println("分配1MB空间");
		
		printMemoryInfo();
		b = new byte[4*1024*1024];
		System.out.println("分配4MB空间");

		printMemoryInfo();
	}
	
	public static void printMemoryInfo() {
		System.out.print("maxMemory=");
		System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024.0 + " MB");
		System.out.print("freeMemory=");
		System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024.0 + " MB");
		System.out.print("totalMemory=");
		System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024.0 + " MB");
	}

}
