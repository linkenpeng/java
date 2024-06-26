package com.intecsec.java.arithmetic.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划
 * Created by Peter.Peng
 * Created on 2022/1/4 18:25
 */
public class DynamicProgramming {

    /**
     * 最大礼物
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
                    continue;
                }
                if(i == 0) {
                    grid[i][j] += grid[i][j - 1] ;
                }
                else if(j == 0) {
                    grid[i][j] += grid[i - 1][j];
                }
                else {
                    grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }

    /**
     * 最长不重复子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int i = -1, res = 0, len = s.length();
        for(int j = 0; j < len; j++) {
            if(dic.containsKey(s.charAt(j))) {
                // 更新左指针 i
                i = Math.max(i, dic.get(s.charAt(j)));
            }
            // 哈希表记录
            dic.put(s.charAt(j), j);
            // 更新结果
            res = Math.max(res, j - i);
        }
        return res;
    }

    /**
     * 动态规划求回文串
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int [][] dp = new int[len][len];
        for(int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for(int j = i+1; j < len; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len-1];
    }
}