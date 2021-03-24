package com.intecsec.java.search.service;

/**
 * @author Peter.Peng
 * @date 2021/3/24
 */
public interface MeetElasticSearchService {

	void add();

	void addBatch();

	void delete();

	void update();

	String get(String id);

	Object search(String username);
}
