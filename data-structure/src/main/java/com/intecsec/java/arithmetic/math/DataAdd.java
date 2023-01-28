package com.intecsec.java.arithmetic.math;

import java.util.*;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-01-21 09:37
 **/
public class DataAdd {

    /**
     * 三数相加等于0
     * @param nums
     * @return
     */
    public List<List<Integer>> findThreeNumsForTarget(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if(len < 1) {
            return ans;
        }

        Arrays.sort(nums);
        for(int first = 0; first < len; ++first) {
            if(first > 0 && nums[first] == nums[first-1]) {
                continue;
            }

            int target = -nums[first];
            int third = len - 1;

            for(int second = first + 1; second < len; ++second) {
                if(second > (first + 1) && nums[second] == nums[second-1]) {
                    continue;
                }

                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }

                if(second == third) {
                    break;
                }

                if(nums[second] + nums[third] == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[first]);
                    temp.add(nums[second]);
                    temp.add(nums[third]);
                    ans.add(temp);
                }
            }
        }

        return ans;
    }



}
