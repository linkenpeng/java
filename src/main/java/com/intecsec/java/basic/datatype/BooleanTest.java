package com.intecsec.java.basic.datatype;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-11-15 16:09
 **/
public class BooleanTest {

    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        System.out.println(atomicBoolean.get());
        replace(atomicBoolean);
        System.out.println(atomicBoolean.get());
    }

    public static void replace(AtomicBoolean atomicBoolean) {
        atomicBoolean.set(true);
    }
}
