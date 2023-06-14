package com.intecsec.java.basic.security;

import java.io.File;


public class FileTest {

	public static void main(String[] args) {
		
		System.setProperty("java.security.policy","file:java-basic/files/policy/dist.policy");
		System.setSecurityManager(new SecurityManager());
		
		File f = new File("/tmp/1.txt");
		if(f.exists()){
			f.delete();
		}
		
	}
}
