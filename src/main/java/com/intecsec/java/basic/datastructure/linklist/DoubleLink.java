package com.intecsec.java.basic.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-11 10:49
 **/
public class DoubleLink {
    public long dData;
    public DoubleLink next;
    public DoubleLink previous;

    public DoubleLink(long d) {
        dData = d;
    }

    public void displayLink() {
        System.out.print(dData + " ");
    }
}
