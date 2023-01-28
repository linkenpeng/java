package com.intecsec.java.arithmetic.stack;

import java.util.LinkedList;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-12-31 20:59
 **/
public class MinStack {

    public LinkedList<Integer> A;
    public LinkedList<Integer> B;

    public MinStack() {
        A = new LinkedList();
        B = new LinkedList();
    }

    public void push(int val) {
        A.add(val);
        if(B.isEmpty() || B.getLast() >= val) {
            B.add(val);
        }
    }

    public void pop() {
        if(A.removeLast().equals(B.getLast())) {
            B.removeLast();
        }
    }

    public int min() {
       return B.getLast();
    }

    public int top() {
        return A.getLast();
    }
}
