package com.intecsec.java.search.split;

import org.ansj.splitWord.analysis.*;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-03-10 16:51
 **/
public class AnsjSeqTest {

    public static void main(String[] args) {
        String content = "君不见，黄河之水天上来，奔流到海不复回。" +
                "君不见，高堂明镜悲白发，朝如青丝暮成雪。人生得意须尽欢，莫使金樽空对月。" +
                "天生我材必有用，千金散尽还复来。烹羊宰牛且为乐，会须一饮三百杯。" +
                "岑夫子，丹丘生，将进酒，杯莫停。";

        // 最小粒度分词
        System.out.println(BaseAnalysis.parse(content));

        // 精准分词
        System.out.println(ToAnalysis.parse(content));

        // 用户自定义词典优先分词
        System.out.println(DicAnalysis.parse(content));

        // 面向索引的分词
        System.out.println(IndexAnalysis.parse(content));

        // 带有新词发现功能的分词
        System.out.println(NlpAnalysis.parse(content));
    }

}
