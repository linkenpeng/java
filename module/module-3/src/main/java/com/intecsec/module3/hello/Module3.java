package com.intecsec.module3.hello;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-06-11 19:43
 **/
public class Module3 {

    public static void main(String[] args) {
        System.out.println("Hello Module3");
        print();
    }

    public static void print() {
        Class<Module3> cls = Module3.class;
        Module module = cls.getModule();
        String moduleName = module.getName();
        System.out.println("Module Name: " + moduleName);

    }
}
