package com.intecsec.java.basic.datastructure.balance;

/**
 * @description: 几种负载均衡的算法，
 * from : <a>https://www.cnblogs.com/szlbm/p/5588555.html</a>
 * @author: peter.peng
 * @create: 2019-06-24 20:28
 **/
public class BalanceTest {

    public static void main(String[] args) {
        System.out.println(IpMap.serverWeightMap);

        String server = WeightRandom.getServer();
        System.out.println(server);
    }
}
