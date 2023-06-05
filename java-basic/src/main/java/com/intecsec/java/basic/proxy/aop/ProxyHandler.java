package com.intecsec.java.basic.proxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;


public class ProxyHandler implements InvocationHandler {
	
	static String beforeMethod = "";
	static String afterMethod = "";
	
	private Person receiverObject;
	
	public ProxyHandler(Person object){
		this.receiverObject = object;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//处理before方法
		if(beforeMethod!=null&&beforeMethod.length()>0){
			ClassLoader cl = ProxyHandler.class.getClassLoader();
			Class<?> c = cl.loadClass(receiverObject.getClass().getName());
			Method m=c.getMethod(beforeMethod);
			Object obj = c.newInstance();
			m.invoke(obj);
		}
		
		//处理目标方法
		Object result = method.invoke(receiverObject, args);
		
		//处理after方法
		if(afterMethod!=null&&afterMethod.length()>0){
			method.invoke(receiverObject, args);
			ClassLoader cl = ProxyHandler.class.getClassLoader();
			Class<?> c = cl.loadClass(receiverObject.getClass().getName());
			Method m=c.getMethod(afterMethod);
			Object obj = c.newInstance();
			m.invoke(obj);
		}
		return result;
	}

}
