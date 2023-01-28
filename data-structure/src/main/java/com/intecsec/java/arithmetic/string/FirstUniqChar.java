package com.intecsec.java.arithmetic.string;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-01-21 09:45
 **/
public class FirstUniqChar {

    /**
     * 首次唯一值
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Boolean> map = new LinkedHashMap<>();
        for (char c : chars) {
            map.put(c, !map.containsKey(c) ? true : false);
        }

        for (Map.Entry<Character, Boolean> entry : map.entrySet()) {
            if(entry.getValue()) {
                return entry.getKey();
            }
        }

        return ' ';
    }
}
