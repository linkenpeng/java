package com.intecsec.java.basic.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-11 11:45
 **/
public class SortedListApp {
    public static void main(String[] args) {
        SortedList theList = new SortedList();

        theList.insert(22);
        theList.insert(44);


        theList.displayList();

        theList.insert(66);

        theList.insert(11);
        theList.insert(33);
        theList.insert(55);

        theList.displayList();

        theList.remove();
        theList.remove();

        theList.displayList();
    }
}
