package com.intecsec.java.arithmetic;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-12-29 21:44
 **/
public class TestMain {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};

        Solution solution = new Solution();
        List<List<Integer>> result = solution.findTowNumForTarget(nums, 10);
        System.out.println(result);
    }

    public static ListNode initLisNode() {
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


    public static void pringLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(6);
        list.add(-1);

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

    public static void testLRUCache() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4, 4);
    }

    public static void testSolution() {
        Solution solution = new Solution();
        PrintUtil.printMsg(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public static void test1() {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(-1);
        minStack.push(2);
        minStack.push(3);

        System.out.println(minStack.min());
        System.out.println(minStack.top());
        minStack.pop();
        minStack.pop();

        System.out.println(minStack.min());
        System.out.println(minStack.top());
    }
}
