package com.intecsec.java.basic.security;

import java.io.*;

public class TxtFileWrite {
	public static void main(String[] args) {
		writeFile1();		
	}

	public static void writeFile1() {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			fos = new FileOutputStream("c:/temp/abc.txt"); // 节点类
			osw = new OutputStreamWriter(fos, "UTF-8"); // 转化类
			bw = new BufferedWriter(osw); // 装饰类
			bw.write("我们是Ecnuers");
			bw.newLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally {
			try {
				bw.close(); // 关闭最后一个类，会将所有的底层流都关闭
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	
}
