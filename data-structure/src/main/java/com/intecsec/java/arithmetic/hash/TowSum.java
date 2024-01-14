package com.intecsec.java.arithmetic.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 两数之和
 * @author: peter.peng
 * @create: 2024-01-14 10:35
 **/
public class TowSum {
    public static void main(String[] args) {
        int[] a = {3,2,4};
        int target = 6;
        int[] result = twoSum(a, target);
        int[] result2 = twoSumHash(a, target);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result2[1]);
        System.out.println(result2[0]);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[j] + nums[i] == target) {
                    return new int[]{i,j};
                }
            }
        }

        return result;
    }

    public static int[] twoSumHash(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(hash.containsKey(target - nums[i])) {
                return new int[]{hash.get(target - nums[i]), i};
            }
            hash.put(nums[i], i);
        }

        return result;
    }
}
