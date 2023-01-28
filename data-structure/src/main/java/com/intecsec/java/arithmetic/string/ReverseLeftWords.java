package com.intecsec.java.arithmetic.string;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-01-21 09:45
 **/
public class ReverseLeftWords {

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

}
