package com.intecsec.java.basic.jvm.metaspace;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class MetaSpaceOOM {
	public static void main(String[] args) throws Exception {
		try{
            int num = 0;
            URL url = new File("java-basic/target/classes/").toURI().toURL();
            URL[] urls = {url};
            List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();
            while(true) {
                ClassLoader classLoader = new URLClassLoader(urls);
                classLoaders.add(classLoader);
                classLoader.loadClass("com.intecsec.java.basic.jvm.HelloMeta");
                num ++;
                System.out.println(num);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
}

