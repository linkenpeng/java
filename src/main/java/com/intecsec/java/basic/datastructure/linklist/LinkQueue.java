package com.intecsec.java.basic.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-11 14:40
 **/
public class LinkQueue {
    private FirstLastList theList;

    public LinkQueue() {
        theList = new FirstLastList();
    }

    public boolean isEmpty() {
        return theList.isEmpty();
    }

    public void insert(long j) {
        theList.insertLast(j);
    }

    public long remove() {
        return theList.deleteFirst();
    }

    public void displayQueue() {
        System.out.print("Queue (front --> rear): ");
        theList.displayList();
    }
}
