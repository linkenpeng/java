package com.intecsec.java.basic.datastructure.balance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @description: 随机
 * @author: peter.peng
 * @create: 2019-06-24 20:25
 **/

public class Random
{
    public static String getServer()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<String, Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(keyList.size());

        return keyList.get(randomPos);
    }

    public static void main(String[] args) {
        String s1 = String.valueOf(System.currentTimeMillis());
        String s2 = String.valueOf(ThreadLocalRandom.current().nextInt(1000, 9999));
        System.out.println(s1);
        System.out.println(s2);
        String random = s1 + s2;
        System.out.println(random);
    }
}