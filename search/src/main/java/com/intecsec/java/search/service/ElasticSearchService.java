package com.intecsec.java.search.service;

/**
 * @author Peter.Peng
 * @date 2021/3/24
 */
public interface ElasticSearchService {

	void add();

	void addBatch();

	void delete(String id);

	void update();

	String get(String id);

	Object search(String username);

	void parseSearchResponse();

	void buildIndexRequestWithString(String indexName, String document);

	void buildIndexRequestWithMap(String indexName, String document);

	void buildIndexRequestWithXContentBuilder(String indexName, String document);

	void buildIndexRequestWithKV(String indexName, String document);

}
