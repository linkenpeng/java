package com.intecsec.java.basic.designpattern.chin;

/**
 * 定义具体处理类
 * @author: peter.peng
 * @create: 2024-01-14 10:16
 **/
public class ConcreteHandlerB extends Handler {

    @Override
    public void handleRequest(Request request) {
        if (request.getType().equals(RequestType.TYPE_B)) {
            System.out.println("ConcreteHandlerB处理请求 " +request.getName());
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}