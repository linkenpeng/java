package com.intecsec.java.basic.jvm.memory;

/**
 * 来自于《实战Java虚拟机》
 * -Xmx20m -Xms20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
 * 新生代1M，eden/s0=2, eden 512KB, s0=s1=256KB 
 * 新生代无法容纳1M，所以直接放老年代
 * 
 * -Xmx20m -Xms20m -Xmn7m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
 * 新生代7M，eden/s0=2, eden=3.5M, s0=s1=1.75M
 * 所以可以容纳几个数组，但是无法容纳所有，因此发生GC
 * 
 * -Xmx20m -Xms20m -Xmn15m -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC
 * 新生代15M，eden/s0=8, eden=12M, s0=s1=1.5M
 * 
 * -Xmx20m -Xms20m -XX:NewRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
 * 新生代是6.6M，老年代13.3M
 * @author Tom
 *
 */
public class NewSizeDemo {

	public static void main(String[] args) {
		
		byte[] b = null;
		for(int i=0;i<10;i++)
		{
			b = new byte[1*1024*1024];
		}		
	}
}
