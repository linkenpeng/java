package com.intecsec.java.basic.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-13 21:49
 **/
public class SortedList {
    private FirstLastLink first;

    public SortedList() {
        first = null;
    }

    public SortedList(FirstLastLink[] linkArr) {
        first = null;
        for (int j = 0; j < linkArr.length; j++) {
            insert(linkArr[j]);
        }
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insert(FirstLastLink newLink) {
        FirstLastLink previous = null;
        FirstLastLink current = first;

        while (current != null && newLink.dData > current.dData) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            first = newLink;
        } else {
            previous.next = newLink;
        }

        newLink.next = current;
    }

    public void insert(long key) {
        FirstLastLink newLink = new FirstLastLink(key);
        insert(newLink);
    }

    public FirstLastLink remove() {
        FirstLastLink temp = first;
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
