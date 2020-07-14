package com.intecsec.java.basic.datastructure.array;


/**
 * @description: 数组基础类
 * @author: peter.peng
 * @create: 2019-01-17 15:07
 **/
public class BaseArray {
    protected long[] a;
    protected int nElems;

    public BaseArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    public void initArray() {
        insert(77);
        insert(99);
        insert(44);
        insert(55);
        insert(22);
        insert(88);
        insert(11);
        insert( 00);
        insert(66);
        insert(3);
    }

    public void initArrayRandom(int n) {
        for(int i = 0; i < n; i++) {
            long d = (int) Math.random() * 99;
            insert(d);
        }
    }

    public boolean find(long searchKey) {
        int j;
        for(j = 0; j < nElems; j++) {
            if(searchKey == a[j]) {
                break;
            }
        }
        if(j == nElems) {
            return false;
        } else {
            return true;
        }
    }

    public void insert(long value) {
        a[nElems] = value;
        nElems++;
    }

    public boolean delete(long value) {
        int j;
        for(j = 0; j < nElems; j++) {
            if(value == a[j]) {
                break;
            }
        }
        if(j == nElems) {
            return false;
        } else {
            for(int k = j; k < nElems; k++) {
                a[k] = a[k+1];
            }
            nElems--;
            return true;
        }
    }

    protected void swap(int one, int tow) {
        long temp = a[one];
        a[one] = a[tow];
        a[tow] = temp;
    }

    public void display() {
        int j;
        for(j = 0; j < nElems; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println(" ");
    }
}
