package com.intecsec.java.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-11 11:07
 **/
public class LinkListApp {

    public static void main(String[] args) {
        LinkList theList = new LinkList();

        theList.insertFirst(22, 2.99);
        theList.insertFirst(44, 4.99);
        theList.insertFirst(66, 6.99);
        theList.insertFirst(88, 8.99);

        theList.displayList();

        Link f = theList.find(44);
        if (f != null) {
            System.out.println("Found link with key " + f.iData);
        } else {
            System.out.println("Can't find link");
        }

        Link d = theList.delete(66);
        if(d != null) {
            System.out.println("Deleted link with key " + d.iData);
        } else {
            System.out.println("Can't delete link");
        }

        while (!theList.isEmpty()) {
            Link aLink = theList.deleteFirst();
            System.out.print("Deleted ");
            aLink.displayLink();
            System.out.println("");
        }

        theList.displayList();
    }
}
