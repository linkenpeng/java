package com.intecsec.java.basic.proxy.dynimic;

import java.lang.reflect.Proxy;

//动态代理模式
public class DynamicProxyDemo {
    public static void main(String[] args) {
    	//1.创建目标对象
    	SubjectImpl realSubject = new SubjectImpl();    
    	
    	//2.创建调用处理器对象
    	ProxyHandler handler = new ProxyHandler(realSubject); 
    	
    	//3.动态生成代理对象
        Subject proxySubject = 
        		(Subject)Proxy.newProxyInstance
        		  (SubjectImpl.class.getClassLoader(),
                   SubjectImpl.class.getInterfaces(), handler); 
        //proxySubject真实类型com.sun.proxy.$Proxy0
        //proxySubject继承Proxy类，实现Subject接口
        //newProxyInstance的第二个参数，就是指定代理对象的接口
        
        //4.客户端通过代理对象调用方法
        //本次调用将自动被代理处理器的invoke方法接收
        proxySubject.request();    
        
        System.out.println(proxySubject.getClass().getName());
        System.out.println(proxySubject.getClass().getSuperclass().getName());
    }
}


