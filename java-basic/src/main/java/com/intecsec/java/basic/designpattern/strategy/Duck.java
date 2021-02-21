package com.intecsec.java.basic.designpattern.strategy;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-02-21 11:35
 **/
public abstract class Duck {

    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {

    }

    public abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void setFlyBehavior(FlyBehavior fb) {
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qc) {
        quackBehavior = qc;
    }

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }

}
