package com.intecsec.module1.hello;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-06-11 19:38
 **/
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello Module1");
        HelloWorld module1 = new HelloWorld();
        module1.print();
    }

    public void print() {
        Class<HelloWorld> cls = HelloWorld.class;
        Module module = cls.getModule();
        String moduleName = module.getName();
        System.out.println("Module Name: " + moduleName);
    }
}
