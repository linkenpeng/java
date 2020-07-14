package com.intecsec.java.basic.generic;

/**
 * @description: 泛型方法
 *
 * 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
 * 这个T可以出现在这个泛型方法的任意位置.
 *
 * @author: peter.peng
 * @create: 2019-09-27 16:06
 **/
public class GenericMethod {

    public static void main(String[] args) {
        test1();
        test2(new Integer(3));
        test3(new int[3], new Object());
    }

    public static <T> void test1() {
        T t = null;
        System.out.println(t);
    }

    public static <T> T test2(T t) {
        System.out.println(t);
        return t;
    }

    public static <T, E> void test3(T t , E e) {
        System.out.println(t);
        System.out.println(e);
    }

    public <T> void printMsg(T... args) {
        for(T t : args) {
            System.out.println(t);
        }
    }

    /**
     * 泛型方法的基本介绍
     * @param tClass 传入的泛型实参
     * @return T 返回值为T类型
     * 说明：
     *     1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     *     3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     *     4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     */
    public <T> T genericMethod(Class<T> tClass) throws InstantiationException,
            IllegalAccessException {
        T instance = tClass.newInstance();
        return instance;
    }

    public <T> T showKeyName(Generic<T> container) {
        System.out.println("container key :" + container.getKey());
        //当然这个例子举的不太合适，只是为了说明泛型方法的特性。
        T test = container.getKey();
        return test;
    }
}
