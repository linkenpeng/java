package com.intecsec.java.basic.classloader.cus;

import java.lang.reflect.Method;

public class ClassLoaderTest
{
   public static void main(String[] args)
   {
	   try
	      {
	         ClassLoader loader = new CryptoClassLoader();
	         //loadClass去加载Hello类
	         //loadClass是ClassLoader默认方法，通过委托双亲去加载类
	         //如加载不到，则调用findClass方法加载
	         Class<?> c = loader.loadClass("com.intecsec.java.basic.classloader.hello.Hello");
	         Method m = c.getMethod("say");
	         m.invoke(c.newInstance());
	         
	         System.out.println(c.getClassLoader().getClass().getName());
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }
   }
}


