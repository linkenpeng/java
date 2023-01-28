package com.intecsec.java.arithmetic.array;

import com.intecsec.java.arithmetic.linkedlist.ListNode;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-01-21 09:13
 **/
public class DoublePointer {

    public static void main(String[] args) {

    }

    /**
     * 判斷一个链表是否有环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        boolean hasCycle = false;

        if(head == null || head.next == null) {
            return hasCycle;
        }

        ListNode nextHead = head.next;
        while (head.next != null) {
            int val = head.val;

            while(nextHead.next != null) {
                if(nextHead.val == val) {
                    return true;
                }
                nextHead = nextHead.next;
            }

            head = head.next;
        }

        return hasCycle;
    }
}
