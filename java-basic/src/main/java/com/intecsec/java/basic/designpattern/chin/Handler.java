package com.intecsec.java.basic.designpattern.chin;

/**
 * 定义一个抽象处理器（Handler）类，其中包含一个指向下一个处理器的引用，并定义一个处理请求的方法。
 * @author: peter.peng
 * @create: 2024-01-14 10:15
 **/
public abstract class Handler {
    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(Request request);
}