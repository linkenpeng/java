package com.intecsec.java.basic.third;

import java.io.IOException;
import java.util.List;

public class BM25Test {
    public static void main(String[] args) throws Exception {
        String query = "GZ五月花";
        BM25Search bm25Search = new BM25Search();
        bm25Search.addDocument("GZ五月花");
        bm25Search.addDocument("GZ动漫星城");
        bm25Search.addDocument("GZ天河好又多店");
        bm25Search.addDocument("BJ西直门凯德");
        List<String> result = bm25Search.search(query);
        System.out.println(result);
    }
}
