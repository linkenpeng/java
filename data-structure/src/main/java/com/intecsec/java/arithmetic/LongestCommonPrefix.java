package com.intecsec.java.arithmetic;

import java.util.Arrays;

/**
 * @description: 最长公共前缀
 * @author: peter.peng
 * @create: 2021-02-01 22:26
 **/
public class LongestCommonPrefix {

    public static void main(String[] args) {

        String[] strs = {"customer", "car", "cat"};
        // String[] strs = {"customer", "car", null};

        System.out.println(commonPrefix(strs));
    }

    public static String commonPrefix(String[] strs) {
        if(!checkStr(strs)) {
            return "";
        }

        int len = strs.length;
        StringBuilder res = new StringBuilder();
        Arrays.sort(strs);

        int m = strs[0].length();
        int n = strs[len - 1].length();
        int num = Math.min(m, n);
        for(int i = 0; i < num; i++) {
            if(strs[0].charAt(i) == strs[len - 1].charAt(i)) {
                res.append(strs[0].charAt(i));
            } else {
                break;
            }
        }

        return res.toString();
    }

    public static boolean checkStr(String[] strs) {
        boolean flag = false;

        if(strs != null) {
            for (int i = 0; i < strs.length; i++) {
                if(strs[i] != null && strs[i].length() != 0) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }
}
