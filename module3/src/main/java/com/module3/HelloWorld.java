package com.module3;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-06-11 14:55
 **/
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello HelloWorld module3");
        print();
    }

    public static void print() {
        Class<HelloWorld> cls = HelloWorld.class;
        Module module = cls.getModule();
        String moduleName = module.getName();
        System.out.println("Module Name: " + moduleName);
    }
}
