package com.intecsec.java.arithmetic;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-12-29 21:44
 **/
public class TestMain {

    public static void main(String[] args) {
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
