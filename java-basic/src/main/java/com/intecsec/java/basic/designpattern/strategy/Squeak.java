package com.intecsec.java.basic.designpattern.strategy;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-02-21 11:33
 **/
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
