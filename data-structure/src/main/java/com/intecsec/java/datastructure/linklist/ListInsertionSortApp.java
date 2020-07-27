package com.intecsec.java.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-11 11:45
 **/
public class ListInsertionSortApp {
    public static void main(String[] args) {
        int size = 10;
        FirstLastLink[] linkArr = new FirstLastLink[size];

        for (int j = 0; j < linkArr.length; j++) {
            long n = (long) (Math.random() * 99);
            FirstLastLink newLink = new FirstLastLink(n);
            linkArr[j] = newLink;
        }

        System.out.println("Un sorted arr:");
        for (int j = 0; j < size; j++ ) {
            System.out.print(linkArr[j].dData + " ");
        }
        System.out.println("");

        SortedList theList = new SortedList(linkArr);
        theList.displayList();

        for(int j = 0; j < size; j++) {
            linkArr[j] = theList.remove();
        }

        System.out.println("Sorted arr:");
        for (int j = 0; j < size; j++ ) {
            System.out.print(linkArr[j].dData + " ");
        }
        System.out.println("");
    }
}
