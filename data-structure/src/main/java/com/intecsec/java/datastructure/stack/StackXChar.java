package com.intecsec.java.datastructure.stack;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-01 14:33
 **/
public class StackXChar {
    private int maxSize;
    private char[] stackArray;
    private int top;

    public StackXChar(int s) {
        maxSize = s;
        stackArray = new char[maxSize];
        top = -1;
    }

    public void push(char j) {
        stackArray[++top] = j;
    }

    public char pop() {
        return stackArray[top--];
    }

    public char peak() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }
}
