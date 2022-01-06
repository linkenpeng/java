package com.intecsec.java.arithmetic;

import java.util.LinkedList;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-12-29 21:44
 **/
public class TestMain {

    public static void main(String[] args) {
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
        System.out.println(cache.get(1));
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
