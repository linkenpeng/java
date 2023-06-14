package com.intecsec.java.basic.classloader.hello;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class HelloThread extends Thread{
	private ClassLoader loader;
	
	public HelloThread(String file, ClassLoader outerLoader)
	{
		try {
			URL url = new URL(file);
			this.loader = new URLClassLoader(new URL[]{url},outerLoader);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void run()
	{
		Class<?> c;
		try {
			c = loader.loadClass("com.intecsec.java.basic.classloader.hello.Hello");
			//采用反射调用
			Object obj = c.newInstance();
			Method m = c.getMethod("say");
			
			while(true)
			{
				m.invoke(obj);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
}
