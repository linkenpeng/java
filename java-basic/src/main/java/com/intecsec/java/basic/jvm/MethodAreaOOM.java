package com.intecsec.java.basic.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: 方法去内存溢出
 *
 * jdk1.8 已经废弃永久代内存设置
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * 改用：元数据空间
 * VM Args: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 *
 * @author: peter.peng
 * @create: 2019-11-24 20:49
 **/
public class MethodAreaOOM {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, args);
                }
            });

            enhancer.create();
        }
    }

    static class OOMObject {

    }

}
