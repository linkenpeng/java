package com.intecsec.java.basic.designpattern.chin;

/**
 * @description:责任链模式
 * @author: peter.peng
 * @create: 2024-01-14 10:18
 **/
public class ChinOfResponsibility {

    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        Handler handlerC = new ConcreteHandlerC();

        handlerA.setSuccessor(handlerB);
        handlerB.setSuccessor(handlerC);

        Request request1 = new Request("已处理 1", RequestType.TYPE_A);
        Request request2 = new Request("已处理 2", RequestType.TYPE_B);
        Request request3 = new Request("已处理 3", RequestType.TYPE_C);

        //handlerA.handleRequest(request1);
        //handlerA.handleRequest(request2);
        handlerA.handleRequest(request3);
    }
}
