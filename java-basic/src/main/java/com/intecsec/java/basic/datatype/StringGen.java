package com.intecsec.java.basic.datatype;

import com.intecsec.java.util.JsonUtils;
import com.intecsec.java.util.MD5Util;
import com.intecsec.java.vo.Coupon;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-03-18 18:41
 **/
public class StringGen {

    public static void main(String[] args) {
        genHashCode();
    }

    public static void genHashCode() {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> mape = new HashMap<>();

        Map<Integer, Integer> bucket= new HashMap<>();
        bucket.put(30, 0);
        bucket.put(70, 0);

        for(int i = 0; i < 10010; i++) {
            String str = String.valueOf(i);
            // str = MD5Util.getMD5(String.valueOf(i));
            int hashCode = str.hashCode();
            // System.out.println(hashCode);
            if (hashCode % 100 < 30) {
                bucket.put(30, bucket.get(30) + 1);
            }
            if (hashCode % 100 >= 30) {
                bucket.put(70, bucket.get(70) + 1);
            }

            if (map.containsKey(hashCode)) {
                if (mape.containsKey(hashCode)) {
                    mape.put(hashCode, mape.get(hashCode) + map.get(hashCode));
                } else {
                    mape.put(hashCode, map.get(hashCode));
                }
            }

            map.put(hashCode, 1);
        }

        System.out.println(bucket);
        System.out.println(mape);
        System.out.println(map.size());
    }

    public static void replaceSpecail1(String str) {
        String username = "银\\\\亿\\格兰郡{}}}}}}\"G3号楼(怒江北街银亿格兰郡G3\n" +
                ")";
        System.out.println(username);
        System.out.println(replaceSpecail(username));


        //language=JSON
        String jsonStr = "{\"name\":\"li\", \"age\":19}";
        System.out.println(jsonStr);

        if (jsonStr == null) {

        }
    }

    public static String replaceSpecail(String str) {
        if(StringUtils.isEmpty(str)) {
            return str;
        }
        return str.replaceAll("(\\r\\n|\\r|\\n|\\n\\r|\\{|}|\"|\'|\\\\)", "");
    }

    public static void replace() {
        String sAll = "http://10.66.3.137:11082/,http://10.66.3.137:11083/";
        String s = "http://10.66.3.137:11082/";
        String newAddress = sAll.replaceAll(s+"[,]?", "");
        System.out.println(newAddress);
    }

    public static void toJson() {
        System.out.println(checkSpecailHandle("1175113551511"));
        Coupon coupon = new Coupon();
        coupon.setId(1);
        coupon.setName("coupon");
        System.out.println(JsonUtils.toJson(coupon));
        System.out.println(coupon);

        String str = "old";
        System.out.println(str);
        replace(str);
        System.out.println(str);
    }

    public static boolean checkSpecailHandle(String oid) {
        List<String> oids = Arrays.asList("51511,0985".split(","));
        return  oids.contains(oid)||Character.isLetter(oid.charAt(oid.length()-1));
    }

    public static void escape() {
        Map<String, String> addtionalMap = new HashMap<>();
        addtionalMap.put("alipayDiscountAmount", "101");
        String additional = StringEscapeUtils.escapeHtml4(JsonUtils.toJson(addtionalMap));
        System.out.println(additional);
    }

    public static void t() {
        long second = 3720000 / 1000L;
        long hour = second / 3600;
        long minute = second % 3600 / 60;
        String result = MessageFormat.format("<font color=\"\">秒杀结束还剩{0}小时{1}分</font>", hour, minute);
        System.out.println(result);
    }

    public static void replace(String str) {
        str = "replaced";
    }
}
