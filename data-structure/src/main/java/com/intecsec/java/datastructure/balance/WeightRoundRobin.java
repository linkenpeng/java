package com.intecsec.java.datastructure.balance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: 权重轮询
 * @author: peter.peng
 * @create: 2019-06-24 20:26
 **/

public class WeightRoundRobin
{
    private static Integer pos;

    public static String getServer()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        List<String> serverList = new ArrayList<>();
        while (iterator.hasNext())
        {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++)
                serverList.add(server);
        }

        String server;
        synchronized (pos)
        {
            if (pos > keySet.size())
                pos = 0;
            server = serverList.get(pos);
            pos ++;
        }

        return server;
    }
}
