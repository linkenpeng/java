package com.intecsec.java.arithmetic.string;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-01-21 09:49
 **/
public class MaxArrow {
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
}
