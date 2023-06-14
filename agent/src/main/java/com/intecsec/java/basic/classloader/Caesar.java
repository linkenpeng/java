package com.intecsec.java.basic.classloader;

import java.io.*;

/**
 * Encrypts a file using the Caesar cipher.
 *
 */
public class Caesar {
	private static final String CLASS_FOLDER = "/Users/pengzhenxian/project/java/java/agent/";
	private static final String CLASS_PACKAGE_PATH = "target/classes/com/intecsec/java/basic/classloader/hello/";


	public static void main(String[] args) throws Exception {
		int key = 3;

		// 将Input类加密，输出到Output中
		String input = CLASS_FOLDER + CLASS_PACKAGE_PATH + "Hello.class";
		String output = CLASS_FOLDER + CLASS_PACKAGE_PATH + "Hello.caesar";

		try (FileInputStream in = new FileInputStream(input); FileOutputStream out = new FileOutputStream(output)) {
			int ch;
			while ((ch = in.read()) != -1) {
				// 每个字节码+key
				byte c = (byte) (ch + key);
				out.write(c);
			}
		}
		System.out.println("Caesar done");
	}
}
