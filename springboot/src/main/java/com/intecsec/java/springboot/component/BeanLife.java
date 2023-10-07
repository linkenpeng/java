package com.intecsec.java.springboot.component;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-09-23 19:07
 **/
@Component
public class BeanLife implements InitializingBean, DisposableBean {

    public BeanLife() {
        System.out.println("constructor");
    }

    @Async
    public void query(){
        System.out.println("query!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @PostConstruct
    public void PostConstruct(){
        System.out.println("PostConstruct");
    }

    public void InitMethod(){
        System.out.println("InitMethod");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @PreDestroy
    public void PreDestroy(){
        System.out.println("PreDestroy");
    }

    public void destroyMethod(){
        System.out.println("destroyMethod");
    }

}
