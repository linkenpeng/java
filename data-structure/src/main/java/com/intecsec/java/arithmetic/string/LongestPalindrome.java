package com.intecsec.java.arithmetic.string;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-12-29 21:23
 **/
public class LongestPalindrome {

    private int index, len;

    /**
     * 最大回文串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        System.out.println(s.toCharArray());
        if(len < 2) {
            return s;
        }

        for (int i = 0; i < len - 1; i++) {
            PalindromeHelper(s, i, i);
            PalindromeHelper(s, i, i + 1);
        }

        return s.substring(index, index + len);
    }

    public void PalindromeHelper(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        if(len < r - l -1) {
            index = l + 1;
            len = r - l - 1;
        }
    }








}
