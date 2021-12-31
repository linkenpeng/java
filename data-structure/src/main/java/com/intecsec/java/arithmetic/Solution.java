package com.intecsec.java.arithmetic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-12-29 21:23
 **/
public class Solution {
    private int index, len;

    /**
     * 最大回文串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        System.out.println(s.toCharArray());
        if(len < 2) {
            return s;
        }

        for (int i = 0; i < len - 1; i++) {
            PalindromeHelper(s, i, i);
            PalindromeHelper(s, i, i + 1);
        }

        return s.substring(index, index + len);
    }

    public void PalindromeHelper(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        if(len < r - l -1) {
            index = l + 1;
            len = r - l - 1;
        }
    }

    /**
     * 动态规划求回文串
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int [][] dp = new int[len][len];
        for(int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for(int j = i+1; j < len; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len-1];
    }

    /**
     * 匹配括号
     * @param s
     * @return
     */
    public int maxArrow(String s) {
        int cnt = 0, max = 0, i;

        for(i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                cnt++;
            } else {
                cnt--;
            }
            max = Math.max(cnt, max);
        }

        return max;
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


}
