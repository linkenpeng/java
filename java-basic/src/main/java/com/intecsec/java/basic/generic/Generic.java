package com.intecsec.java.basic.generic;

/**
 * @description: 泛型
 * @author: peter.peng
 * @create: 2018-12-23 16:02
 **/
public class Generic<T> {
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    //虽然在方法中使用了泛型，但是这并不是一个泛型方法。
    //这只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。
    //所以在这个方法中才可以继续使用 T 这个泛型。
    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
