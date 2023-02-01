package com.intecsec.java.arithmetic.linkedlist;

import com.intecsec.java.arithmetic.GenData;
import com.intecsec.java.arithmetic.PrintUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-01-21 09:40
 **/
public class PrintLinkedList {

    public static void printLinkedList() {
        LinkedList<Integer> list = GenData.genLinkedList();

        PrintUtil.printMsg(list);
        PrintUtil.printMsg(list.getFirst());
        PrintUtil.printMsg(list.getLast());
        PrintUtil.printMsg(list.removeFirst());
        PrintUtil.printMsg(list);
        PrintUtil.printMsg(list.removeLast());
        PrintUtil.printMsg(list);
        // 出队
        PrintUtil.printMsg(list.poll());
        PrintUtil.printMsg(list);

        list.addLast(-1);
        PrintUtil.printMsg(list);

        PrintUtil.printMsg(list.pop());
    }

    /**
     * 从尾到头打印链表
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {

        Deque<Integer> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = stack.pop();
        }

        return res;
    }

    public int[] reversePrint2(ListNode head) {
        ListNode node = head;
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }

        int[] res = new int[size];
        node = head;
        while (node != null) {
            res[--size] = node.val;
            node = node.next;
        }

        return res;
    }
}
