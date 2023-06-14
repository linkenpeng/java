package com.intecsec.java.basic.classloader;

public class ClassLoaderTree {

	public static void main(String[] args) {
		ClassLoader c = ClassLoaderTree.class.getClassLoader();
		while(null != c)
		{
			System.out.println(c.getClass().getName());
			c = c.getParent();
		}
		if(null == c){
			System.out.println("BootstrapLoader is implemented by C ");
		}
	}
}


