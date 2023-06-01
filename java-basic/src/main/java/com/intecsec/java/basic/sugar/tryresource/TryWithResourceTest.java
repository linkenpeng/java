package com.intecsec.java.basic.sugar.tryresource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TryWithResourceTest {

	public static void main(String[] args) {
		readFile1();
		readFile2();
	}
	
	public static void readFile1() {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream("c:/temp/abc.txt"); // 节点类
			isr = new InputStreamReader(fis, "UTF-8"); // 转化类
			//isr = new InputStreamReader(fis);
			br = new BufferedReader(isr); // 装饰类
			// br = new BufferedReader(new InputStreamReader(new
			// FileInputStream("c:/temp/abc.txt")))
			String line;
			while ((line = br.readLine()) != null) // 每次读取一行
			{
				System.out.println(line);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				br.close(); // 关闭最后一个类，会将所有的底层流都关闭
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void readFile2() {
		String line;
		//try-resource 语句，自动关闭资源
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("c:/temp/abc.txt")))) {
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		}
		catch(Exception ex)  
		{
			ex.printStackTrace();
		}
	}
}
