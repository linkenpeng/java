package com.intecsec.java.datastructure.queue;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-01 16:22
 **/
public class PriorityQ {
    int maxSize;
    private long[] queueArray;
    private int nItems;

    public PriorityQ(int s) {
        maxSize = s;
        queueArray = new long[maxSize];
        nItems = 0;
    }

    public void insert(long item) {
        int j;
        if(nItems == 0) {
            queueArray[nItems++] = item;
        } else {
            for(j = nItems - 1; j >= 0; j--) {
                if(item > queueArray[j]) {
                    queueArray[j+1] = queueArray[j];
                } else {
                    break;
                }
            }
            queueArray[j+1] = item;
        }
        nItems++;
    }

    public long remove() {
        return queueArray[--nItems];
    }

    public long peekMin() {
        return queueArray[nItems-1];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int size() {
        return nItems;
    }
}
