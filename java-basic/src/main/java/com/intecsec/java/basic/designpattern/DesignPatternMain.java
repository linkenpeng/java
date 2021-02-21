package com.intecsec.java.basic.designpattern;

import com.intecsec.java.basic.designpattern.strategy.Duck;
import com.intecsec.java.basic.designpattern.strategy.FlyRockedPowerd;
import com.intecsec.java.basic.designpattern.strategy.ModelDuck;
import com.intecsec.java.basic.designpattern.strategy.MuteQuack;
import com.intecsec.java.basic.designpattern.template.ConcreteClass1;
import com.intecsec.java.basic.designpattern.template.ConcreteClass2;

/**
 * @description: 设计模式运行测试
 *
 * OO基础：抽象 封装 多态 继承
 *
 * OO原则：
 * 封装变化
 * 多用组合，少用继承
 * 针对接口编程，不针对实现编程
 *
 * @author: peter.peng
 * @create: 2021-02-21 10:42
 **/
public class DesignPatternMain {

    public static void main(String[] args) {
        strategy();
    }

    /**
     * 模板方法模式：<br>
     * <p>
     * 在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中。
     * 模板方法使得子类可以在不改变算法结构的情况下，重新定义算法中的步骤。
     * </p>
     */
    public static void template() {
        ConcreteClass1 concreteClass1 = new ConcreteClass1();
        concreteClass1.templateMethod();

        ConcreteClass2 concreteClass2 = new ConcreteClass2();
        concreteClass2.templateMethod();
    }

    /**
     * 策略模式：<br>
     * <p>
     * 定义了算法族，分别封装起来，让他们之间可以相互替换，此模式让算法的变化独立于使用算法的客户
     * </p>
     */
    public static void strategy() {
        Duck modelDuck = new ModelDuck();
        modelDuck.display();
        modelDuck.performFly();
        modelDuck.performQuack();

        modelDuck.setFlyBehavior(new FlyRockedPowerd());
        modelDuck.performFly();
        modelDuck.setQuackBehavior(new MuteQuack());
        modelDuck.performQuack();
    }
}
