package com.intecsec.java.basic.third;

import java.util.*;

public class BM25 {

    private static final double k1 = 1.2; // BM25参数k1
    private static final double b = 0.75; // BM25参数b

    public static void main(String[] args) {
        // 假设的词组列表
        List<String> phrases = Arrays.asList("春天的故事", "秋天的童话", "夏天的传说", "冬天的神话");

        // 假设的文档频率，即每个词组在文档集中出现的次数
        Map<String, Integer> docFrequencies = new HashMap<>();
        docFrequencies.put("春天的故事", 2);
        docFrequencies.put("秋天的童话", 5);
        docFrequencies.put("夏天的传说", 3);
        docFrequencies.put("冬天的神话", 1);

        // 假设的文档总数
        int totalDocs = 100;

        // 假设的查询词组
        String query = "秋天";

        // 计算BM25得分
        double maxScore = Double.MIN_VALUE;
        String mostRelevantPhrase = null;

        for (String phrase : phrases) {
            double score = calculateBM25Score(phrase, docFrequencies, totalDocs, query);
            if (score > maxScore) {
                maxScore = score;
                mostRelevantPhrase = phrase;
            }
        }

        System.out.println("最相关的词组是: " + mostRelevantPhrase + ", 得分: " + maxScore);
    }

    private static double calculateBM25Score(String phrase, Map<String, Integer> docFrequencies, int totalDocs, String query) {
        int queryLength = query.length();
        int phraseLength = phrase.length();
        int queryFrequency = countFrequency(query, phrase);

        double score = 0;
        for (int i = 0; i < queryLength; i++) {
            String term = query.substring(i, i + 1);
            int df = docFrequencies.getOrDefault(term, 0);
            double idf = Math.log((double) totalDocs / df);
            double tf = (double) queryFrequency / queryLength;
            double avgdl = (double) phraseLength / totalDocs;
            double scoreForTerm = idf * tf * (k1 + 1) / (k1 * (1 - b + b * avgdl) + tf);
            score += scoreForTerm;
        }
        return score;
    }

    private static int countFrequency(String query, String phrase) {
        int frequency = 0;
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.substring(i, i + 1).equals(query)) {
                frequency++;
            }
        }
        return frequency;
    }
}