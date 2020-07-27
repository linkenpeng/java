package com.intecsec.java.datastructure.queue;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-01 16:22
 **/
public class Queue {
    int maxSize;
    private long[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    public Queue(int s) {
        maxSize = s;
        queueArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(long j) {
        if(rear == maxSize - 1) {
            rear = -1;
        }
        queueArray[++rear] = j;
        nItems++;
    }

    public long remove() {
        long temp = queueArray[front++];
        if(front == maxSize) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    public long peekFront() {
        return queueArray[front];
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
