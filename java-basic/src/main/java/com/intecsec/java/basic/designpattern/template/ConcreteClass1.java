package com.intecsec.java.basic.designpattern.template;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-02-21 10:38
 **/
public class ConcreteClass1 extends AbstractClass {

    @Override
    public void primitiveOperation1() {
        System.out.println("ConcreteClass1 primitiveOperation1");
    }

    @Override
    public void primitiveOperation2() {
        System.out.println("ConcreteClass1 primitiveOperation2");
    }

    @Override
    public void hook() {
        System.out.println("ConcreteClass1 hook");
    }
}
