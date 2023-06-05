package com.intecsec.java.basic.proxy.dynimic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类的调用处理器
 */
class ProxyHandler implements InvocationHandler{
    private Subject subject;
    public ProxyHandler(Subject subject){
        this.subject = subject;
    }
    
    //此函数在代理对象调用任何一个方法时都会被调用。
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
    	System.out.println(proxy.getClass().getName());
    	//定义预处理的工作，当然你也可以根据 method 的不同进行不同的预处理工作
        System.out.println("====before====");
        Object result = method.invoke(subject, args);
        System.out.println("====after====");
        return result;
    }
}