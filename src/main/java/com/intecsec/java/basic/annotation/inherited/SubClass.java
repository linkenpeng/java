package com.intecsec.java.basic.annotation.inherited;

/**
 * @description: 子类
 * @author: peter.peng
 * @create: 2018-07-05 16:10
 **/
public class SubClass  extends ParentClass{
    //子类实现父类的抽象方法
    @Override
    public void abstractMethod() {
        System.out.println("子类实现父类的abstractMethod抽象方法");
    }

    //子类继承父类的doExtends方法

    //子类覆盖父类的doHandle方法
    @Override
    public void doHandle(){
        System.out.println("子类覆盖父类的doHandle方法");
    }
}
