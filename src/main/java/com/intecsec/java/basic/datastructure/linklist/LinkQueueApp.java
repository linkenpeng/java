package com.intecsec.java.basic.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-11 14:44
 **/
public class LinkQueueApp {
    public static void main(String[] args) {
        LinkQueue theQueue = new LinkQueue();
        theQueue.insert(20);
        theQueue.insert(40);

        theQueue.displayQueue();

        theQueue.insert(60);
        theQueue.insert(80);

        theQueue.displayQueue();

        theQueue.remove();
        theQueue.remove();

        theQueue.displayQueue();
    }
}
