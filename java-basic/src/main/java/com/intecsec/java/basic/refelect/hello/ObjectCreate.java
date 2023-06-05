package com.intecsec.java.basic.refelect.hello;

import com.intecsec.java.basic.refelect.hello.A;
import com.intecsec.java.basic.refelect.hello.B;
import com.intecsec.java.basic.refelect.hello.C;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ObjectCreate {

	public static void main(String[] args) throws Exception{
		//第一种 直接new 调用构造函数
		com.intecsec.java.basic.refelect.hello.A obj1 = new com.intecsec.java.basic.refelect.hello.A();
		obj1.hello();		
		
		
		//第二种 clone  
		//obj3 是obj2的克隆对象  没有调用构造函数
		com.intecsec.java.basic.refelect.hello.B obj2 = new com.intecsec.java.basic.refelect.hello.B();
		obj2.hello();		
		
		com.intecsec.java.basic.refelect.hello.B obj3 = (B) obj2.clone();
		obj3.hello();
		
		//第三种 序列化  没有调用构造函数
		//序列化会引发安全漏洞，未来将被移除出JDK，请谨慎使用！！！
		C obj4  = new C();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"));   
	    out.writeObject(obj4);   
	    out.close();   
	       
	    ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"));   
	    C obj5 = (C) in.readObject();   
	    in.close();   
	    obj5.hello(); 
		
		
		//第四种  newInstance  调用构造函数	    
		Object obj6 = Class.forName("A").newInstance();		
		Method m = Class.forName("A").getMethod("hello");
		m.invoke(obj6);
		
		com.intecsec.java.basic.refelect.hello.A obj7 = (com.intecsec.java.basic.refelect.hello.A) Class.forName("A").newInstance();
		
		//第五种  newInstance  调用构造函数
		Constructor<com.intecsec.java.basic.refelect.hello.A> constructor = com.intecsec.java.basic.refelect.hello.A.class.getConstructor();
		A obj8 = constructor.newInstance();
		obj8.hello();	
		
	}
}
