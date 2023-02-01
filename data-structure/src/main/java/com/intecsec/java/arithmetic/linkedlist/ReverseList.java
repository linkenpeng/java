package com.intecsec.java.arithmetic.linkedlist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-01-21 09:42
 **/
public class ReverseList {

    /**
     * 翻转列表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while(head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    public ListNode copyRandomList(ListNode head) {
        ListNode newHead = head;
        while (head != null) {
            newHead.val = head.val;
            newHead.next = head;
            newHead.random = head.random;
        }
        return newHead;
    }

}
