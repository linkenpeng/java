package com.intecsec.java.arithmetic;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-12-29 21:44
 **/
public class TestMain {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        printList(solution.reverseList(l1));

        //int[] res = solution.reversePrint2(l1);
        //printIntArray(res);
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
