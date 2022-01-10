package com.intecsec.java.arithmetic;

import java.util.*;

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

    public ListNode copyRamdonList(ListNode head) {
        ListNode newHead = head;
        while (head != null) {
            newHead.val = head.val;
            newHead.next = head;
            newHead.random = head.random;
        }
        return newHead;
    }

    /**
     * 旋转左字符
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        String left = "";
        String right = "";
        for (int i = 0; i < chars.length; i++) {
            if(i < n) {
                right += chars[i];
            } else {
                left += chars[i];
            }
        }

        return left + right;
    }


    /**
     * 首次唯一值
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Boolean> map = new LinkedHashMap<>();
        for (char c : chars) {
            map.put(c, !map.containsKey(c) ? true : false);
        }

        for (Map.Entry<Character, Boolean> entry : map.entrySet()) {
            if(entry.getValue()) {
                return entry.getKey();
            }
        }

        return ' ';
    }

    /**
     * 求最大面积
     * @param nums
     * @return
     */
    public int maxArea(int[] nums) {
        int l = 0, r = nums.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(nums[l], nums[r]) * (r - l);
            ans = Math.max(area, ans);
            if(nums[l] <= nums[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }

}
