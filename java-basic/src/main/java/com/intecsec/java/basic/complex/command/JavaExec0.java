package com.intecsec.java.basic.complex.command;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavaExec0 {
	public static void main(String[] args) {
		Process p;
		String cmd = "ifconfig";  //查看ip地址

		try {
			// 执行命令
			p = Runtime.getRuntime().exec(cmd);
			// 取得命令结果的输出流
			InputStream fis = p.getInputStream();
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(fis);
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			// 直到读完为止
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			System.out.println("");
            int exitVal = p.waitFor(); //获取进程最后返回状态
            System.out.println("Process exitValue: " + exitVal);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}