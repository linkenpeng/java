package com.intecsec.java.arithmetic.doublepointer;

import com.intecsec.java.arithmetic.linkedlist.ListNode;

/**
 * @description:
 * @author: peter.peng
 * @create: 2024-01-14 21:26
 **/
public class DoublePointer {

    /**
     * 零移动
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        System.out.println(left + " : " + right);
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * 判斷一个链表是否有环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        boolean hasCycle = false;

        if(head == null || head.next == null) {
            return hasCycle;
        }

        ListNode nextHead = head.next;
        while (head.next != null) {
            int val = head.val;

            while(nextHead.next != null) {
                if(nextHead.val == val) {
                    return true;
                }
                nextHead = nextHead.next;
            }

            head = head.next;
        }

        return hasCycle;
    }
}
