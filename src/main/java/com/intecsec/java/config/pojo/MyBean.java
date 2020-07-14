package com.intecsec.java.config.pojo;

/**
 * @description:
 * @author: peter.peng
 * @create: 2020-02-05 11:50
 **/
public class MyBean {

    String name;

    public MyBean(String name) {
        this.name = name;
    }


    public MyBean(){
        System.out.println("generate MyBean Instance");
    }

    public void init(){
        System.out.println("MyBean Resources Initialized");
    }

}
