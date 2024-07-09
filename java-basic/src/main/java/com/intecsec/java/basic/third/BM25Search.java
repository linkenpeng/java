package com.intecsec.java.basic.third;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BM25Search {

    private Directory directory;
    private IndexWriter indexWriter;
    private IndexReader indexReader;
    private IndexSearcher indexSearcher;
    private QueryParser queryParser;

    public BM25Search() throws IOException {
        directory = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(new SmartChineseAnalyzer());
        indexWriter = new IndexWriter(directory, config);
        indexReader = DirectoryReader.open(indexWriter);
        indexSearcher = new IndexSearcher(indexReader);
        queryParser = new MultiFieldQueryParser(new String[]{"content"}, new SmartChineseAnalyzer());
    }

    public void addDocument(String content) throws IOException {
        Document document = new Document();
        document.add(new TextField("content", content, Field.Store.YES));
        indexWriter.addDocument(document);
        indexWriter.commit();
    }

    public List<String> search(String queryStr) throws Exception {
        Query query = queryParser.parse(queryStr);
        TopDocs topDocs = indexSearcher.search(query, 10);
        List<String> results = new ArrayList<>();
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            results.add(doc.get("content"));
        }
        return results;
    }

    public void close() throws IOException {
        indexWriter.close();
        indexReader.close();
    }
}