package com.intecsec.java.arithmetic;

import com.intecsec.java.arithmetic.linkedlist.ListNode;
import com.intecsec.java.arithmetic.stack.MinStack;

import java.util.LinkedList;

public class GenData {

    public static ListNode genLisNode() {
        ListNode l1 = new ListNode(1);

        ListNode l2 = new ListNode(2);
        l1.next = l2;

        ListNode l3 = new ListNode(3);
        l2.next = l3;

        ListNode l4 = new ListNode(4);
        l3.next = l4;

        ListNode l5 = new ListNode(5);
        l4.next = l5;

        ListNode l6 = new ListNode(6);
        l5.next = l6;

        ListNode l7 = new ListNode(7);
        l6.next = l7;

        return l1;
    }

    public static LinkedList<Integer> genLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(6);
        list.add(-1);
        return list;
    }

    public static int[] gentIntArray() {
        return new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
    }

    public static MinStack genStack() {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(-1);
        minStack.push(2);
        minStack.push(3);
        return minStack;
    }
}
