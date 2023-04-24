package com.intecsec.java.vo;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-07-13 08:34
 **/
public class CarBase {
    public String name;

    public CarBase() {
        System.out.println("CarBase");
    }

    public CarBase(String name) {
        this.name = name;
        System.out.println(this.name);
    }
}
