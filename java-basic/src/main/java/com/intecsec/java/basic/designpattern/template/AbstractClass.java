package com.intecsec.java.basic.designpattern.template;

/**
 * @description: 模板方法模式
 * @author: peter.peng
 * @create: 2021-02-21 10:35
 **/
abstract class AbstractClass {

    public final void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
        concreteOperation();
        hook();
    }

    abstract void primitiveOperation1();

    abstract void primitiveOperation2();

    public final void concreteOperation() {
        System.out.println("AbstractClass concreteOperation");
    }

    public void hook() {

    }

}
