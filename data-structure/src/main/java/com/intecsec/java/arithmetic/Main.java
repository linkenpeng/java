package com.intecsec.java.arithmetic;

import com.intecsec.java.arithmetic.hash.LRUCache;
import com.intecsec.java.arithmetic.math.DataAdd;
import com.intecsec.java.arithmetic.math.MaxArea;
import com.intecsec.java.arithmetic.stack.MinStack;

import java.util.List;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-12-29 21:44
 **/
public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] nums2 = new int[]{1, 0, 2, -1, 3, -2, -1};

        DataAdd solution = new DataAdd();
        List<List<Integer>> result = solution.findThreeNumsForTarget(nums2);
        System.out.println(result);
    }

    public static void testLRUCache() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4, 4);
    }

    public static void testMaxArea() {
        MaxArea maxArea = new MaxArea();
        PrintUtil.printMsg(maxArea.maxArea(GenData.gentIntArray()));
    }

    public static void testStack() {
        MinStack minStack = GenData.genStack();

        System.out.println(minStack.min());
        System.out.println(minStack.top());
        minStack.pop();
        minStack.pop();

        System.out.println(minStack.min());
        System.out.println(minStack.top());
    }
}
