package com.intecsec.java.basic.classloader;

/**
 * -Xbootclasspath/a:agent/target/classes/
 */
public class WorkerTest {
	public static void main(String[] args) {
		new Worker().say();
	}
}
