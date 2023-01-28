package com.intecsec.java.arithmetic.string;

/**
 * @description:
 * @author: peter.peng
 * @create: 2022-01-09 09:59
 **/
public class StringToInt {

    /**
     * 字符转数字
     * @param s
     * @return
     */
    public int strToInt(String s) {
        int res = 0;
        int i = 0, sign = 1, temp = Integer.MAX_VALUE / 10, len = s.length();
        while (' ' == s.charAt(i)) {
            if (++i == len) {
                return 0;
            }
        }
        if(s.charAt(i) == '-') {
            sign = -1;
        }
        if(s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }
        for (int j = i; j < len; j++) {
            if(s.charAt(j) < '0' || s.charAt(j) > '9') {
                break;
            }
            if(res > temp || res == temp && s.charAt(j) > '7') {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (s.charAt(i) - '0');
        }

        return sign * res;
    }

}
