package com.intecsec.java.search.service.impl;

import com.intecsec.java.search.service.ScrollSearchService;
import com.intecsec.java.search.util.ParseElasticResponse;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Peter.Peng
 * @date 2021/3/29
 */
@Service
@Slf4j
public class ScrollSearchServiceImpl implements ScrollSearchService {

	@Autowired
	private RestHighLevelClient client;

	@Override
	public void scrollSearchRequest(String indexName, String keyword) {
		SearchRequest searchRequest = new SearchRequest(indexName);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("username", keyword));
		searchSourceBuilder.size(8);
		searchRequest.source(searchSourceBuilder);
		searchRequest.scroll(TimeValue.timeValueMinutes(1));

		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			ParseElasticResponse.parseSearchResponse(searchResponse);

			String scrollId = searchResponse.getScrollId();
			SearchHits hits = searchResponse.getHits();
			while (hits != null && hits.getHits().length != 0) {
				SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
				scrollRequest.scroll(TimeValue.timeValueSeconds(30));
				SearchResponse searchScrollResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
				scrollId = searchScrollResponse.getScrollId();
				hits = searchScrollResponse.getHits();
				log.info("scrollId:{}", scrollId);
				log.info("total hits is:{}, row hits is:{}", hits.getTotalHits().value, hits.getHits().length);
			}

			clearScrollRequest(scrollId);
		} catch (Exception e) {
			log.info("处理异常", e);
		}
	}

	private void clearScrollRequest(String scrollId) {
		ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
		clearScrollRequest.addScrollId(scrollId);
		try {
			ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
			boolean success = clearScrollResponse.isSucceeded();
			int released = clearScrollResponse.getNumFreed();
			log.info("success:{}, release:{}", success, released);
		} catch (IOException e) {
			log.info("处理异常", e);
		}
	}


}
