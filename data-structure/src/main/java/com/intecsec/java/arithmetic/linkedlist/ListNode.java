package com.intecsec.java.arithmetic.linkedlist;

/**
 * @description: 单向链表
 * @author: peter.peng
 * @create: 2021-12-29 23:28
 **/
public class ListNode {
    public int val;

    public ListNode next;
    public ListNode random;

    public ListNode(int x) {
        this.next = null;
        this.random = null;
        val = x;
    }
}
