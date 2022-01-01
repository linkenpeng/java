package com.intecsec.java.arithmetic;

/**
 * @description: 单向链表
 * @author: peter.peng
 * @create: 2021-12-29 23:28
 **/
public class ListNode {
    int val;

    ListNode next;
    ListNode random;

    ListNode(int x) {
        this.next = null;
        this.random = null;
        val = x;
    }
}
