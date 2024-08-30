package com.intecsec.java.basic.third;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BM25Matcher {
    /**
     * 常量k，用来限制TF值的增长极限 默认1.2
     */
    private final float k1;
    /**
     * b是一个常数，它的作用是规定L对评分的影响有多大
     */
    private final float b;

    public BM25Matcher(float k1, float b) {
        this.k1 = k1;
        this.b = b;
    }

    // 假设这个函数用于计算文档（SKU描述）中词项的频率
    private int calculateTermFrequency(String document, String term) {
        // 这里使用简单的字符串查找和计数，实际应用中可能需要更复杂的分词和统计
        int count = 0;
        int index = 0;
        while ((index = document.indexOf(term, index)) != -1) {
            count++;
            index += term.length(); // 避免重叠计数
        }
        return count;
    }

    /**
     * 假设这个函数用于计算逆文档频率（IDF），这里简化为固定值，实际中需要基于索引计算
     */
    private double calculateIDF(String term, Map<String, Integer> termDocFreqs, int totalDocs) {
        // 假设termDocFreqs包含所有词项及其文档频率，totalDocs是文档总数
        // 这里简化为一个固定值，实际中应根据termDocFreqs和totalDocs计算
        return Math.log((double) totalDocs / (double) (termDocFreqs.getOrDefault(term, 1) + 1));
    }

    /**
     * BM25 得分计算函数
     */
    public double score(String query, String document, Map<String, Integer> termDocFreqs, int totalDocs, int avgDocLength) {
        // 假设 query 和 document 已经被分词处理，这里直接按空格分词
        String[] queryTerms = query.split("\\s+");
        int docLength = document.length(); // 简化为字符长度

        double score = 0.0;
        for (String term : queryTerms) {
            int termFreq = calculateTermFrequency(document, term);
            if (termFreq > 0) {
                double idf = calculateIDF(term, termDocFreqs, totalDocs);
                double tf = (double) termFreq * (k1 + 1);
                double lengthNorm = (double) docLength / (k1 * (1 - b + b * (docLength / avgDocLength)));
                double bm25 = idf * (tf / (tf + k1 * (1 - b + b * lengthNorm)));
                score += bm25;
            }
        }

        // 可以选择对总得分进行归一化处理
        return score;
    }

    // 测试函数
    public static void main(String[] args) {
        BM25Matcher matcher = new BM25Matcher(1.2f, 0.75f);

        // 假设的输入数据
        Map<String, Integer> termDocFreqs = new HashMap<>();
        termDocFreqs.put("欧莱雅", 20);
        termDocFreqs.put("青春", 15);
        termDocFreqs.put("密码", 10);
        termDocFreqs.put("经典", 5);

        int totalDocs = 100;
        int avgDocLength = 100;

        String query = "欧莱雅";
        List<String> nameList = Lists.newArrayList();
        nameList.add("欧莱雅青春密码密集肌能精华液15ml");
        nameList.add("欧莱雅复颜专研抗皱紧致眼霜7.5ml");
        nameList.add("欧莱雅清润葡萄籽强韧修护膜力水65ml");
        nameList.add("欧莱雅复颜精纯维C透亮鲜注面膜5片");
        nameList.add("欧莱雅青春密码密集肌能精华液7.5ml");
        nameList.add("欧莱雅复颜专研抗皱紧致乳50ml");
        nameList.add("欧莱雅复颜精纯维C透亮鲜注面膜1pc");
        nameList.add("欧莱雅复颜经典抗皱5件装套组（130ml+110ml+样70ml）");
        nameList.add("欧莱雅值得拥有帆布袋");
        nameList.add("欧莱雅值得拥有纸袋");
        nameList.add("欧莱雅值得拥有礼盒");

        nameList.forEach(name -> {
            double score = matcher.score(query, name, termDocFreqs, totalDocs, avgDocLength);
            System.out.println("name:【" + name + "】的相关性得分为：" + score);
        });

    }
}