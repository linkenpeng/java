package com.intecsec.java.basic.annotation.inherited;

import java.lang.reflect.Method;

/**
 * @description: 注解继承测试
 * @author: peter.peng
 * @create: 2018-07-05 16:15
 **/
public class MainTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<SubClass> clazz = SubClass.class;
        if(clazz.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation cla = clazz.getAnnotation(MyAnnotation.class);
            System.out.println("子类继承到父类类上Annotation,其信息如下：" + cla.value());
        } else {
            System.out.println("子类没有继承到父类类上Annotation");
        }

        Method method = clazz.getMethod("abstractMethod", new Class[]{});
        if(method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation ma = method.getAnnotation(MyAnnotation.class);
            System.out.println("子类实现父类的abstractMethod抽象方法，继承到父类抽象方法中的Annotation,其信息如下：" + ma.value());
        } else {
            System.out.println("子类实现父类的abstractMethod抽象方法，没有继承到父类抽象方法中的Annotation");
        }

        Method method2 = clazz.getMethod("doExtends", new Class[]{});
        if(method2.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation ma = method2.getAnnotation(MyAnnotation.class);
            System.out.println("子类继承父类的doExtends，继承到父类抽象方法中的Annotation,其信息如下：" + ma.value());
        } else {
            System.out.println("子类继承父类的doExtends，没有继承到父类抽象方法中的Annotation");
        }

        Method method3 = clazz.getMethod("doHandle", new Class[]{});
        if(method3.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation ma = method3.getAnnotation(MyAnnotation.class);
            System.out.println("子类覆盖父类的doHandle，继承到父类抽象方法中的Annotation,其信息如下：" + ma.value());
        } else {
            System.out.println("子类覆盖父类的doHandle，没有继承到父类抽象方法中的Annotation");
        }



    }
}
