package com.intecsec.java.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-11 11:37
 **/
public class FirstLastLink {
    public long dData;
    public FirstLastLink next;

    public FirstLastLink(long d) {
        dData = d;
    }

    public void displayLink() {
        System.out.print(dData + " ");
    }
}
