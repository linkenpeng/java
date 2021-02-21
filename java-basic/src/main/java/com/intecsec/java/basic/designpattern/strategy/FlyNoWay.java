package com.intecsec.java.basic.designpattern.strategy;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-02-21 11:31
 **/
public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}
