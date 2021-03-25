package com.intecsec.java.search.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.intecsec.java.search.service.ElasticSearchService;
import com.intecsec.java.vo.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
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
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Peter.Peng
 * @date 2021/3/24
 */
@Service
@Slf4j
public class ElasticSearchServiceImpl implements ElasticSearchService {

	@Autowired
	private RestHighLevelClient client;

	private static final String indexName = "posts";

	@Override
	public void add() {
		log.info("创建单个索引");
		Member member = new Member(1, "linken", 1, "1990-08-12");
		try {
			String jsonData = new ObjectMapper().writeValueAsString(member);
			IndexRequest indexRequest = new IndexRequest(indexName).id(String.valueOf(member.getId())).source(jsonData, XContentType.JSON);
			//同步执行
			IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
			processIndexResponse(indexResponse);
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
				request.add(new IndexRequest(indexName).id(String.valueOf(m.getId())).source(data, XContentType.JSON));
			}
			BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
			for(BulkItemResponse itemResponse : bulkResponse) {
				DocWriteResponse docWriteResponse = itemResponse.getResponse();
				if(itemResponse.getOpType() == DocWriteRequest.OpType.INDEX
						|| itemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
					IndexResponse indexResponse = (IndexResponse) docWriteResponse;
					log.info("处理索引操作的响应：" + indexResponse);
				} else if(itemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
					UpdateResponse updateResponse = (UpdateResponse) docWriteResponse;
					log.info("处理更新操作的响应：" + updateResponse);
				} else if (itemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
					DeleteResponse deleteResponse = (DeleteResponse) docWriteResponse;
					log.info("处理删除操作的响应：" + deleteResponse);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String id) {
		DeleteRequest request = new DeleteRequest(indexName, id);
		try {
			DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
			processDeleteResponse(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processDeleteResponse(DeleteResponse deleteResponse) {
		String index = deleteResponse.getIndex();
		String id = deleteResponse.getId();
		Long version = deleteResponse.getVersion();
		Result result = deleteResponse.getResult();
		log.info("delete document, index:{}, id:{}, version:{}", index, id, version);

		ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
		if(shardInfo.getTotal() != shardInfo.getSuccessful()) {
			log.info("Successed shards are not enough.");
		}

		if(shardInfo.getFailed() > 0) {
			for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
				String reason = failure.reason();
				log.info("reason:{}, id:{}, index:{}", reason, id, index);
			}
		}
	}

	@Override
	public void update() {

	}

	@Override
	public String get(String id) {
		checkExists(indexName, id);
		checkExistsAsync(indexName, id);

		GetRequest getRequest = new GetRequest(indexName, id);
		getRequest.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);

		String[] includes = new String[] {"username", "birthday"};
		String[] excludes = Strings.EMPTY_ARRAY;
		FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
		getRequest.fetchSourceContext(fetchSourceContext);

		/*includes = Strings.EMPTY_ARRAY;
		excludes = new String[] {"username"};
		fetchSourceContext = new FetchSourceContext(true, includes, excludes);
		getRequest.fetchSourceContext(fetchSourceContext);*/

		getRequest.storedFields("username");

		try {
			GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
			// String username = getResponse.getField("username").getValue();
			// log.info("username:{}", username);
			processGetResponse(getResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}

		getRequest.routing("routing");
		getRequest.preference("preference");
		getRequest.realtime(false);
		getRequest.refresh(true);
		getRequest.version(2);
		getRequest.versionType(VersionType.EXTERNAL);

		return "";
	}

	private void checkExists(String indexName, String id) {
		GetRequest getRequest = new GetRequest(indexName, id);
		getRequest.fetchSourceContext(new FetchSourceContext(false));
		getRequest.storedFields("_none_");
		try {
			boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
			log.info("index:{}, document:{} exits:{}", indexName, id, exists);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkExistsAsync(String indexName, String id) {
		GetRequest getRequest = new GetRequest(indexName, id);
		getRequest.fetchSourceContext(new FetchSourceContext(false));
		getRequest.storedFields("_none_");

		ActionListener<Boolean> listener = new ActionListener<Boolean>() {
			@Override
			public void onResponse(Boolean aBoolean) {
				log.info("index:{}, document:{} async exits:{}", indexName, id, aBoolean);
			}

			@Override
			public void onFailure(Exception e) {

			}
		};

		client.existsAsync(getRequest, RequestOptions.DEFAULT, listener);
	}

	private void processGetResponse(GetResponse getResponse) {
		String index = getResponse.getIndex();
		String id = getResponse.getId();
		log.info("index:{}, id:{}", index, id);
		if(getResponse.isExists()) {
			Long version = getResponse.getVersion();
			String sourceAsString = getResponse.getSourceAsString();
			Map<String, Object> sourceMap = getResponse.getSourceAsMap();
			byte[] sourceAsBytes = getResponse.getSourceAsBytes();
			log.info("version is:{}, sourceAsString is :{}, map:{}, byte:{}", version, sourceAsString, sourceMap, sourceAsBytes);
		}
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

	@Override
	public void parseSearchResponse() {

	}

	@Override
	public void buildIndexRequestWithString(String indexName, String document) {
		IndexRequest request = new IndexRequest(indexName);
		request.id(document);
		String jsonString = "{\"id\":5,\"username\":\"daniel\",\"level\":1,\"birthday\":\"1979-03-12\"}";
		request.source(jsonString, XContentType.JSON);

		try {
			IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void buildIndexRequestWithMap(String indexName, String document) {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("id", 6);
		jsonMap.put("username", "kevin");
		jsonMap.put("level", 6);
		jsonMap.put("birthday", "1970-03-12");
		IndexRequest request = new IndexRequest(indexName).id(document)
				.source(jsonMap);

		ActionListener listener = giveListener();
		client.indexAsync(request, RequestOptions.DEFAULT, listener);
	}

	private void processIndexResponse(IndexResponse indexResponse) {
		String index = indexResponse.getIndex();
		String id = indexResponse.getId();
		Long version = indexResponse.getVersion();
		Result result = indexResponse.getResult();
		log.info("index:{}, id:{}, version:{}", index, id, version);

		if(result == Result.CREATED) {
			log.info("document is created.");
		} else if(result == Result.UPDATED) {
			log.info("document is update.");
		}

		ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
		if(shardInfo.getTotal() != shardInfo.getSuccessful()) {
			log.info("Successed shards are not enough.");
		}

		if(shardInfo.getFailed() > 0) {
			for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
				String reason = failure.reason();
				log.info("reason:{}, id:{}, index:{}", reason, id, index);
			}
		}
	}

	private ActionListener giveListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void onResponse(Object o) {
				log.info("index success");
			}

			@Override
			public void onFailure(Exception e) {
				log.info("index fail", e);
			}
		};
		return listener;
	}

	@Override
	public void buildIndexRequestWithXContentBuilder(String indexName, String document) {
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder();
			builder.startObject();
			{
				builder.field("id", 7);
				builder.field("username", "sam");
				builder.field("level", 7);
				builder.field("birthday", "1989-03-15");
			}
			builder.endObject();
			IndexRequest request = new IndexRequest(indexName).id(document)
					.source(builder);

			ActionListener listener = giveListener();
			client.indexAsync(request, RequestOptions.DEFAULT, listener);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void buildIndexRequestWithKV(String indexName, String document) {
		IndexRequest request = new IndexRequest(indexName).id(document)
				.source("id", 8, "username", "cindy", "level", 3, "birthday", "1972-03-12");

		request.routing("routing");

		request.timeout(TimeValue.timeValueSeconds(1));
		request.timeout("1s");

		request.version(2);
		request.versionType(VersionType.EXTERNAL);

		request.opType(DocWriteRequest.OpType.CREATE);
		request.opType("create");

		request.setPipeline("pipeline");

		try {
			IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
