package com.intecsec.java.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-11 11:38
 **/
public class FirstLastList {
    private FirstLastLink first;
    private FirstLastLink last;

    public FirstLastList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertFirst(long dd) {
        FirstLastLink newLink = new FirstLastLink(dd);

        if(isEmpty()) {
            last = newLink;
        }

        newLink.next = first;
        first = newLink;
    }

    public void insertLast(long dd) {
        FirstLastLink newLink = new FirstLastLink(dd);

        if(isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
        }

        last = newLink;
    }

    public long deleteFirst() {
        long temp = first.dData;
        if(first.next == null) {
            last = null;
        }
        first = first.next;
        return temp;
    }

    public void displayList() {
        System.out.print("List (first --> last): ");
        FirstLastLink current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }
}
