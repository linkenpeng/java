package com.intecsec.java.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-14 11:38
 **/
public class DoubleLinkedList {
    private DoubleLink first;
    private DoubleLink last;

    public DoubleLinkedList() {
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertFirst(long dd) {
        DoubleLink newLink = new DoubleLink(dd);

        if(isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;
        }

        newLink.next = first;

        first = newLink;
    }

    public void insertLast(long dd) {
        DoubleLink newLink = new DoubleLink(dd);

        if(isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.previous = last;
        }

        last = newLink;
    }

    public DoubleLink deleteFirst() {
        DoubleLink temp = first;
        if(first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }

        first = first.next;
        return temp;
    }

    public DoubleLink deleteLast() {
        DoubleLink temp = last;
        if(first.next == null) {
            first = null;
        } else {
            last.previous.next = null;
        }

        last = last.previous;
        return temp;
    }

    public boolean insertAfter(long key, long dd) {
        DoubleLink current = first;
        while (current.dData != key) {
            current = current.next;
            if(current == null) {
                return false;
            }
        }

        DoubleLink newLink = new DoubleLink(dd);
        if(current == last) {
            newLink.next = null;
            last = newLink;
        } else {
            newLink.next = current.next;
            current.previous.next = newLink;
        }

        newLink.previous = current;
        current.next = newLink;

        return true;
    }

    public DoubleLink deleteKey(long key) {
        DoubleLink current = first;

        while (current.dData != key) {
            current = current.next;
            if(current == null) {
                return null;
            }
        }

        if(current == first) {
            first = current.next;
        } else {
            current.previous.next = current.next;
        }

        if(current == last) {
            last = current.previous;
        } else {
            current.next.previous = current.previous;
        }

        return current;
    }

    public void displayForward() {
        System.out.print("List (first --> last): ");
        DoubleLink current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

    public void displayBackward() {
        System.out.print("List (last --> first): ");
        DoubleLink current = last;
        while (current != null) {
            current.displayLink();
            current = current.previous;
        }
        System.out.println("");
    }
}
