package com.intecsec.java.datastructure.array;

/**
 * @description: 数组封装起来
 * @author: peter.peng
 * @create: 2019-01-17 15:07
 **/
public class LowArray {
    private long[] a;

    public LowArray(int size) {
        a = new long[size];
    }

    public void setElem(int index, long value) {
        a[index] = value;
    }

    public long getElem(int index) {
        return a[index];
    }
}
