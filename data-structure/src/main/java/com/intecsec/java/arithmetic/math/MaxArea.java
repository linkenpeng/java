package com.intecsec.java.arithmetic.math;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-01-21 09:48
 **/
public class MaxArea {

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
