package com.intecsec.java.arithmetic;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-12-29 21:44
 **/
public class TestMain {

    public static void main(String[] args) {
        Solution solution = new Solution();
        printMsg(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public static void printS(String s) {
        System.out.println(s);
    }

    public static <T> void printMsg(T... args) {
        for(T t : args) {
            System.out.println(t);
        }
    }

    public static void printList(ListNode head) {
        for(; head != null;) {
            System.out.print(head.val);
            System.out.print(" ");
            head = head.next;
        }
    }

    public static void printIntArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i);
            System.out.print(" ");
        }
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
