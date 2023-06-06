package com.intecsec.java.basic.annotations.basic.inherited;

/**
 * @description: 父类
 * @author: peter.peng
 * @create: 2018-07-05 16:07
 **/
@MyAnnotation(value = "类名上的注解")
public abstract class ParentClass {

    @MyAnnotation(value = "父类的abstractMethod方法")
    public abstract void abstractMethod();

    @MyAnnotation(value = "父类的doExtends方法")
    public void doExtends() {
        System.out.println(" ParentClass doExtends ...");
    }

    @MyAnnotation(value = "父类的doHandle方法")
    public void doHandle(){
        System.out.println(" ParentClass doHandle ...");
    }
}
