package com.intecsec.java.search.service.impl;

import com.intecsec.java.search.service.MultiSearchService;
import com.intecsec.java.search.util.ParseElasticResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.MultiSearchResponse.Item;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Peter.Peng
 * @date 2021/3/29
 */
@Slf4j
@Service
public class MultiSearchServiceImpl implements MultiSearchService {

	@Autowired
	private RestHighLevelClient client;

	private static final String indexName = "posts";

	@Override
	public void multiSearch(List<String> keyWordList) {
		MultiSearchRequest multiSearchRequest = new MultiSearchRequest();

		for(String keyWord : keyWordList) {
			SearchRequest searchRequest = new SearchRequest(indexName);
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.matchQuery("username", keyWord));
			searchRequest.source(searchSourceBuilder);
			multiSearchRequest.add(searchRequest);
		}

		try {
			MultiSearchResponse multiSearchResponse = client.msearch(multiSearchRequest, RequestOptions.DEFAULT);
			processMultiSearchResponse(multiSearchResponse);
		} catch (IOException e) {
			log.info("处理异常", e);
		}
	}

	private void processMultiSearchResponse(MultiSearchResponse multiSearchResponse) {
		Item[] items = multiSearchResponse.getResponses();
		if(ArrayUtils.isEmpty(items)) {
			log.info("items is null.");
			return;
		}

		for(Item item : items) {
			Exception exception = item.getFailure();
			if(exception != null) {
				log.info("exception is " + exception.toString());
			}
			SearchResponse searchResponse = item.getResponse();
			ParseElasticResponse.parseSearchResponse(searchResponse);
		}
	}

}
