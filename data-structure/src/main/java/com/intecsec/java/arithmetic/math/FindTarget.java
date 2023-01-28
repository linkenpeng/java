package com.intecsec.java.arithmetic.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-01-21 09:43
 **/
public class FindTarget {

    /**
     * 找出一个数组满足两个数字相加的所有组合的索引
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> findTowNumForTarget(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if(len < 1) {
            return ans;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < len; i++) {
            if(map.containsKey(target - nums[i])) {
                List<Integer> temp = new ArrayList<>();
                temp.add(map.get(target - nums[i]));
                temp.add(i);
                ans.add(temp);
            }
            map.put(nums[i], i);
        }

        return ans;
    }

}
