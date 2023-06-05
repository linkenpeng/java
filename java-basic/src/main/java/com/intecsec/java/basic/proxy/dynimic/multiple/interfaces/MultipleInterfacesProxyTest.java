package com.intecsec.java.basic.proxy.dynimic.multiple.interfaces;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class MultipleInterfacesProxyTest {

	public static void main(String[] args) throws Exception {
        Cook cook = new CookImpl();
        ClassLoader cl = MultipleInterfacesProxyTest.class.getClassLoader();
        ProxyHandler handler = new ProxyHandler(cook);
        
        //生成代理类型
        Class<?> proxyClass = Proxy.getProxyClass(cl, new Class<?>[]{Cook.class, Driver.class});
                
        //生成代理对象
        Object proxy = proxyClass.getConstructor(new Class[]{InvocationHandler.class}).
                newInstance(new Object[]{handler});
        System.out.println(Proxy.isProxyClass(proxyClass));
        
        Proxy p = (Proxy) proxy;
        System.out.println(p.getInvocationHandler(proxy).getClass().getName());        
        System.out.println("proxy类型:" + proxyClass.getName());
        
        //代理对象都继承于java.lang.reflect.Proxy，但是获取父类确是Object而不是Proxy
        Class father = proxyClass.getSuperclass();
        System.out.println("proxy的父类类型:" + father.getName());
        
        Class[] cs = proxy.getClass().getInterfaces();
        for(Class c:cs)
        {
        	System.out.println("proxy的父接口类型:" + c.getName());
        }
        System.out.println("=====================");
        
        Method[] ms = proxy.getClass().getMethods();
        for(Method m:ms)
        {
        	System.out.println("调用方法 " + m.getName() + "；参数为 " + Arrays.deepToString(m.getParameters()));
        }
        System.out.println("=====================");
        
        Cook c = (Cook) proxy;
        c.doWork();
        
        Driver d = (Driver) proxy;
        d.doWork();     
    }
}
