package com.intecsec.java.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-11 14:24
 **/
public class LinkStatck {
    private LinkList theList;

    LinkStatck() {
        theList = new LinkList();
    }

    public void push(int iData, double dData) {
        theList.insertFirst(iData, dData);
    }

    public Link pop() {
        Link link = theList.deleteFirst();
        return link;
    }

    public boolean isEmpty() {
        return theList.isEmpty();
    }

    public void displayStack() {
        System.out.print("Stack (top --> bottom): ");
        theList.displayList();
    }
}
