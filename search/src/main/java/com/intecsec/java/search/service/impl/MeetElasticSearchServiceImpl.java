package com.intecsec.java.search.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.intecsec.java.search.service.MeetElasticSearchService;
import com.intecsec.java.vo.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Peter.Peng
 * @date 2021/3/24
 */
@Service
@Slf4j
public class MeetElasticSearchServiceImpl implements MeetElasticSearchService {

	@Autowired
	private RestHighLevelClient client;

	@Override
	public void add() {
		log.info("创建单个索引");
		Member member = new Member(1, "linken", 1, "1990-08-12");
		try {
			String jsonData = new ObjectMapper().writeValueAsString(member);
			IndexRequest indexRequest = new IndexRequest("posts").id(String.valueOf(member.getId())).source(jsonData, XContentType.JSON);
			//同步执行
			IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

			String index = indexResponse.getIndex();
			String id = indexResponse.getId();
			Long version = indexResponse.getVersion();
			Result result = indexResponse.getResult();
			if(result == Result.CREATED) {
				log.info("创建");
			} else if(result == Result.UPDATED) {
				log.info("覆盖");
			}
			ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
			if(shardInfo.getTotal() != shardInfo.getSuccessful()) {

			}
			if(shardInfo.getFailed() > 0) {
				for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
					String reason = failure.reason();
					log.info("reason:{}, id:{}, index:{}", reason, id, index);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addBatch() {
		log.info("批量创建索引");

		List<Member> list = Lists.newArrayList();
		list.add(new Member(2, "leo", 2, "1991-08-12"));
		list.add(new Member(3, "nick", 3, "1993-02-15"));
		list.add(new Member(4, "richard", 4, "1989-05-22"));

		try {
			BulkRequest request = new BulkRequest();
			for(Member m : list) {
				String data = new ObjectMapper().writeValueAsString(m);
				request.add(new IndexRequest("posts").id(String.valueOf(m.getId())).source(data, XContentType.JSON));
			}
			BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
			for(BulkItemResponse itemResponse : bulkResponse) {
				DocWriteResponse docWriteResponse = itemResponse.getResponse();
				if(itemResponse.getOpType() == DocWriteRequest.OpType.INDEX
						|| itemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
					IndexResponse indexResponse = (IndexResponse) docWriteResponse;
					System.out.println("处理索引操作的响应：" + indexResponse);
				} else if(itemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
					UpdateResponse updateResponse = (UpdateResponse) docWriteResponse;
					System.out.println("处理更新操作的响应：" + updateResponse);
				} else if (itemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
					DeleteResponse deleteResponse = (DeleteResponse) docWriteResponse;
					System.out.println("处理删除操作的响应：" + deleteResponse);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete() {

	}

	@Override
	public void update() {

	}

	@Override
	public String get(String id) {
		GetRequest getRequest = new GetRequest("posts", id);
		try {
			GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
			String index = getResponse.getIndex();
			String findId = getResponse.getId();
			if(getResponse.isExists()) {
				Long version = getResponse.getVersion();
				String sourceAsString = getResponse.getSourceAsString();
				return sourceAsString;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public Object search(String username) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchSourceBuilder.query(QueryBuilders.termQuery("username", username));
		searchSourceBuilder.from(0);
		searchSourceBuilder.size(10);
		searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

		HighlightBuilder highlightBuilder = new HighlightBuilder();
		HighlightBuilder.Field highLightTitle = new HighlightBuilder.Field("username");
		highLightTitle.highlighterType("unified");
		highlightBuilder.field(highLightTitle);
		highlightBuilder.preTags("<span style=\"color:red\">");
		highlightBuilder.postTags("</span>");
		searchSourceBuilder.highlighter(highlightBuilder);

		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("posts");
		searchRequest.source(searchSourceBuilder);

		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHits hits = searchResponse.getHits();
			TotalHits totalHits = hits.getTotalHits();
			long numHits = totalHits.value;
			TotalHits.Relation relation = totalHits.relation;
			float maxScore = hits.getMaxScore();
			SearchHit[] searchHits = hits.getHits();

			Float score = null;
			List<Map<String, Object>> list = Lists.newArrayList();
			for(SearchHit hit : searchHits) {
				String index = hit.getIndex();
				String id = hit.getId();
				score = hit.getScore();
				Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();
				Map<String, Object> stringObjectMap = hit.getSourceAsMap();
				list.add(stringObjectMap);
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
