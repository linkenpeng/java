package com.intecsec.java.basic.annotations.runtime;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;


@Fruit(name = "Apple")
public class Main {
    public static void main(String[] args) throws Exception{
    	Fruit fruit = Main.class.getAnnotation(Fruit.class);
    	System.out.println(fruit.name());//Apple
    	
    	System.out.println(fruit.getClass().getName());
    	//com.sun.proxy.$Proxy1
    	System.out.println(fruit.getClass().getGenericInterfaces()[0]);
    	//interface Fruit
    	
    	System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    	InvocationHandler h = Proxy.getInvocationHandler(fruit);
        System.out.println(h.getClass().getName());
        //sun.reflect.annotation.AnnotationInvocationHandler
        
        Field f = h.getClass().getDeclaredField("memberValues");
        f.setAccessible(true);
        
        Map memberValues = (Map) f.get(h);  
        
        //only contain "name" key
        for(Object o : memberValues.keySet())
        {
        	System.out.println(o.toString());
        }
        
        //change the value of "name" key
        memberValues.put("name", "Pear");
        System.out.println(fruit.name()); //Pear
    }
}

