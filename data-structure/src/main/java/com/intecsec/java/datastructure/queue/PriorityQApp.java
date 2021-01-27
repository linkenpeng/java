package com.intecsec.java.datastructure.queue;

/**
 * @description: 优先级队列测试
 * @author: peter.peng
 * @create: 2019-02-01 17:11
 **/
public class PriorityQApp {
    public static void main(String[] args) {
        PriorityQ theQ = new PriorityQ(15);

        theQ.insert(30);
        theQ.insert(50);
        theQ.insert(10);
        theQ.insert(40);
        theQ.insert(20);

        while (!theQ.isEmpty()) {
            long item = theQ.remove();
            System.out.print(item + " ");
        }
    }
}
