package com.intecsec.java.basic.classloader.hello;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class HelloTest {
	private static final String CLASS_FOLDER = "agent";

	public static void main(String[] args) throws Exception {
		//test1和test2展示URLClassLoader加载
		test1();
		test2();
		
		//展示不同线程使用不同的URLClassLoader
		//加载不同路径下同名的类
//		test3();
		
		//展示jar文件热部署
		// test4();
	}
	
	public static void test1() throws Exception
	{
		//URL支持http, https, file, jar 四种协议
		URL url = new URL("file:" + CLASS_FOLDER + "/target/classes/");
		
		//程序运行时，添加一个classpath路径
		URLClassLoader loader = new URLClassLoader(new URL[]{url});
		Class<?> c = loader.loadClass("com.intecsec.java.basic.classloader.hello.Hello");
		
		//采用反射调用
		Method m = c.getMethod("say");
        m.invoke(c.newInstance());
        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());
	}
	public static void test2() throws Exception
	{
		URL url = new URL("file:" + CLASS_FOLDER + "/target/agent-0.0.1-SNAPSHOT.jar");
		
		//程序运行时，添加一个classpath路径
		URLClassLoader loader = new URLClassLoader(new URL[]{url});
		Class<?> c = loader.loadClass("com.intecsec.java.basic.classloader.hello.Hello");
		
		//采用反射调用
		Method m = c.getMethod("say");
        m.invoke(c.newInstance());
	}
	
	public static void test3() throws Exception
	{
		String project1 = "file:" + CLASS_FOLDER + "/target/classes/";
		String project2 = "file:" + CLASS_FOLDER + "/target/classes/";
		
		new HelloThread(project1, Thread.currentThread().getContextClassLoader()).start();
		new HelloThread(project2, Thread.currentThread().getContextClassLoader()).start();
	}
	
	public static void test4() throws Exception{
		String file = "file:" + CLASS_FOLDER + "/target/agent-0.0.1-SNAPSHOT.jar";
		URL url = new URL(file);
		URLClassLoader loader = new URLClassLoader(new URL[]{url});
		int count = 0;
		while(true){
			Class<?> c = loader.loadClass("com.intecsec.java.basic.classloader.hello.Hello");
			Method m = c.getMethod("say");
	        m.invoke(c.newInstance());
			Thread.sleep(1000);
			if(++count%5==0){
				System.out.println("reload");
				loader.close();
				loader = new URLClassLoader(new URL[]{url});
			}
		}

	}
}
