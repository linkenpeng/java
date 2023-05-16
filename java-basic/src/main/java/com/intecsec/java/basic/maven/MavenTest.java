package com.intecsec.java.basic.maven;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.apache.commons.math3.util.ArithmeticUtils;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-14 09:49
 **/
public class MavenTest {
    public static void main(String[] args) {
        chinese();
    }

    public static void gcd() {
        int a = ArithmeticUtils.gcd(30, 60);
        System.out.println(a);
    }

    public static void chinese() {
        String original = "我是中国人，我爱我的祖国，中国万岁。";
        String result = ZhConverterUtil.toTraditional(original);
        System.out.println(result);
    }
}
