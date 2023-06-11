package com.intecsec.module2.hello;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-06-11 19:40
 **/
public class HelloWorld {

    public static void main(String[] args) {
        new com.intecsec.module1.hello.HelloWorld().print();

        System.out.println("Hello Module2");
        print();
    }

    public static void print() {
        Class<HelloWorld> cls = HelloWorld.class;
        Module module = cls.getModule();
        String moduleName = module.getName();
        System.out.println("Module Name: " + moduleName);

    }
}
