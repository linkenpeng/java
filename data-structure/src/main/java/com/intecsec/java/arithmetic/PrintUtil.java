package com.intecsec.java.arithmetic;

/**
 * @description:
 * @author: peter.peng
 * @create: 2022-01-02 19:07
 **/
public class PrintUtil {

    public static void printS(String s) {
        System.out.println(s);
    }

    public static <T> void printMsg(T... args) {
        for(T t : args) {
            System.out.println(t);
        }
    }

    public static void printNodeList(ListNode head) {
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

}
