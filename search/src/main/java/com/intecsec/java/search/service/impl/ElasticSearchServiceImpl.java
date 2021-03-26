package com.intecsec.java.search.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.intecsec.java.search.service.ElasticSearchService;
import com.intecsec.java.vo.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.client.core.TermVectorsResponse.TermVector;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.ReindexRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	// 格式化日期用
	private static final SimpleDateFormat MY_SDF = new SimpleDateFormat("yyyy-MM-dd");

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
			log.info("操作异常", e);
		}
	}

	@Override
	public void addBatch() {
		log.info("批量创建索引");

		List<Member> list = giveList();

		try {
			BulkRequest request = new BulkRequest();
			for(Member m : list) {
				String data = new ObjectMapper().writeValueAsString(m);
				request.add(new IndexRequest(indexName).id(String.valueOf(m.getId())).source(data, XContentType.JSON));
			}

			request.add(new DeleteRequest(indexName).id("4"));
			request.add(new UpdateRequest(indexName, "2").doc(XContentType.JSON, "username", "leo.peng"));

			// 同步方式
			BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
			processBulkResponse(bulkResponse);

			// 异步方式
			ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {
				@Override
				public void onResponse(BulkResponse bulkResponse) {
					log.info("异步方式返回");
					processBulkResponse(bulkResponse);
				}

				@Override
				public void onFailure(Exception e) {

				}
			};
			client.bulkAsync(request, RequestOptions.DEFAULT, listener);

		} catch (Exception e) {
			log.info("操作异常", e);
		}

	}

	private List<Member> giveList() {
		List<Member> list = Lists.newArrayList();
		list.add(new Member(2, "leo", 2, "1991-08-12"));
		list.add(new Member(3, "nick", 3, "1993-02-15"));
		list.add(new Member(4, "richard", 4, "1989-05-22"));
		list.add(new Member(10, "kk", 4, "1990-05-22"));
		return list;
	}

	private void processBulkResponse(BulkResponse bulkResponse) {
		for(BulkItemResponse itemResponse : bulkResponse) {
			DocWriteResponse docWriteResponse = itemResponse.getResponse();
			if(itemResponse.getOpType() == DocWriteRequest.OpType.INDEX
					|| itemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
				IndexResponse indexResponse = (IndexResponse) docWriteResponse;
				log.info("索引操作的响应：" + indexResponse);
			} else if(itemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
				UpdateResponse updateResponse = (UpdateResponse) docWriteResponse;
				log.info("更新操作的响应：" + updateResponse);
			} else if (itemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
				DeleteResponse deleteResponse = (DeleteResponse) docWriteResponse;
				log.info("删除操作的响应：" + deleteResponse);
			}
		}
	}

	@Override
	public void buildBulkRequestWithProcessor() {
		BulkProcessor.Listener listener = new BulkProcessor.Listener() {
			@Override
			public void beforeBulk(long l, BulkRequest bulkRequest) {
				int numberOfActions = bulkRequest.numberOfActions();
				log.info("Executing bulk:{} with {} requests ", l, numberOfActions);
			}

			@Override
			public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
				if(bulkResponse.hasFailures()) {
					log.info("Bulk {} executed with falures", l);
				} else {
					log.info("Bulk {} completed with in {} milliseconds", l, bulkResponse.getTook().getMillis());
				}
			}

			@Override
			public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {
				log.error("Failed to execute bulk", throwable);
			}
		};

		BulkProcessor bulkProcessor = BulkProcessor.builder(
				(bulkRequest, bulkResponseActionListener)
				-> client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, bulkResponseActionListener), listener
				).build();
		try {
			List<Member> list = giveList();
			for(Member m : list) {
				String data = new ObjectMapper().writeValueAsString(m);
				bulkProcessor.add(new IndexRequest(indexName).id(String.valueOf(m.getId())).source(data, XContentType.JSON));
			}
		} catch (Exception e) {
			log.info("操作异常", e);
		}
	}

	@Override
	public void delete(String id) {
		DeleteRequest request = new DeleteRequest(indexName, id);
		try {
			DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
			processDeleteResponse(response);
		} catch (IOException e) {
			log.info("操作异常", e);
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
	public void update(String id) {

		UpdateRequest request = new UpdateRequest(indexName, id);

		request.routing("routing");
		request.timeout(TimeValue.timeValueSeconds(1));
		request.timeout("1s");

		request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);

		request.retryOnConflict(3);
		request.fetchSource(true);

		String[] includes = new String[] {"id", "r*"};
		String[] excludes = Strings.EMPTY_ARRAY;
		request.fetchSource(new FetchSourceContext(true, includes,  excludes));

		includes = Strings.EMPTY_ARRAY;
		excludes = new String[] {"id"};
		request.fetchSource(new FetchSourceContext(true, includes,  excludes));

		// 方式1
		String jsonString = "{\"id\":5,\"username\":\"daniel\",\"level\":1,\"birthday\":\"1979-03-12\"}";
		request.doc(jsonString, XContentType.JSON);

		// 方式2
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("id", 6);
		jsonMap.put("username", "kevin");
		jsonMap.put("level", 6);
		jsonMap.put("birthday", "1970-03-12");
		request.doc(jsonMap);

		// 方式3
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
			request.doc(builder);
		} catch (IOException e) {
			log.info("操作异常", e);
		}

		// 方式4
		request.doc("id", 8, "username", "cindy", "level", 3, "birthday", getBirthDay("1972-03-12"));

		// 同步方式
		try {
			UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
			processUpdateResponse(updateResponse);
		} catch (IOException e) {
			log.info("操作异常", e);
		}

		// 异步方式
		ActionListener listener = new ActionListener<UpdateResponse>() {
			@Override
			public void onResponse(UpdateResponse updateResponse) {
				processUpdateResponse(updateResponse);
			}

			@Override
			public void onFailure(Exception e) {

			}
		};

		client.updateAsync(request, RequestOptions.DEFAULT, listener);
	}

	private long getBirthDay(String birthDay) {
		try {
			return MY_SDF.parse(birthDay).getTime();
		} catch (ParseException e) {
			log.info("操作异常", e);
		}
		return System.currentTimeMillis() / 1000L;
	}

	private void processUpdateResponse(UpdateResponse response) {
		String index = response.getIndex();
		String id = response.getId();
		Long version = response.getVersion();
		Result result = response.getResult();
		log.info("update document, index:{}, id:{}, version:{}", index, id, version);

		if(result == Result.CREATED) {
			log.info("doc create");
		} else if(result == Result.UPDATED) {
			log.info("doc update" + result.toString());
		} else if(result == Result.DELETED) {
			log.info("doc delete " + result.toString());
		} else if(result == Result.NOOP) {
			log.info("no doc");
		}
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

			return getResponse.getSourceAsString();
		} catch (IOException e) {
			log.info("操作异常", e);
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
			log.info("操作异常", e);
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
	public String multiGet(String[] ids) {
		MultiGetRequest request = new MultiGetRequest();
		for(String id : ids) {
			request.add(new MultiGetRequest.Item(indexName, id));
		}
		try {
			MultiGetResponse response = client.mget(request, RequestOptions.DEFAULT);
			processMultiGetResponse(response);
			return response.getResponses().toString();
		} catch (IOException e) {
			log.info("操作异常", e);
		}
		return null;
	}

	private void processMultiGetResponse(MultiGetResponse response) {
		MultiGetItemResponse[] responses = response.getResponses();
		for(MultiGetItemResponse itemResponse : responses) {
			GetResponse getResponse = itemResponse.getResponse();
			processGetResponse(getResponse);
		}
	}

	@Override
	public void reIndex(String from, String to) {
		ReindexRequest request = new ReindexRequest();
		request.setSourceIndices(from);
		request.setDestIndex(to);

		try {
			// 同步方式
			BulkByScrollResponse response = client.reindex(request, RequestOptions.DEFAULT);
			processBulkByScrollResponse(response);

			// 异步方式
			ActionListener<BulkByScrollResponse> listener = new ActionListener<BulkByScrollResponse>() {
				@Override
				public void onResponse(BulkByScrollResponse bulkByScrollResponse) {
					processBulkByScrollResponse(response);
				}

				@Override
				public void onFailure(Exception e) {

				}
			};
			client.reindexAsync(request, RequestOptions.DEFAULT, listener);

		} catch (IOException e) {
			log.info("操作异常", e);
		}
	}

	private void processBulkByScrollResponse(BulkByScrollResponse response) {
		TimeValue timeValue = response.getTook();
		log.info("time is {}", timeValue.getMillis());
		long totalDocs = response.getTotal();
		long updatedDocs = response.getUpdated();
		log.info("totalDocs:{}, updatedDocs:{}", totalDocs, updatedDocs);
	}

	@Override
	public Object search(String username) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		// 查询条件
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		//searchSourceBuilder.query(QueryBuilders.termQuery("username", username));
		//MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("username", username).fuzziness(Fuzziness.AUTO);
		//searchSourceBuilder.query(matchQueryBuilder);

		searchSourceBuilder.from(0);
		searchSourceBuilder.size(10);
		searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

		// 高亮
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		HighlightBuilder.Field highLightTitle = new HighlightBuilder.Field("username");
		highLightTitle.highlighterType("unified");
		highlightBuilder.field(highLightTitle);
		highlightBuilder.preTags("<span style=\"color:red\">");
		highlightBuilder.postTags("</span>");
		searchSourceBuilder.highlighter(highlightBuilder);

		/*// 请求聚合
		TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("by_username").field("username");
		aggregationBuilder.subAggregation(AggregationBuilders.avg("average_age").field("level"));
		searchSourceBuilder.aggregation(aggregationBuilder);*/

		// 建议请求
		SuggestionBuilder termSuggestionBuilder = SuggestBuilders.termSuggestion("username").text(username);
		SuggestBuilder suggestBuilder = new SuggestBuilder();
		suggestBuilder.addSuggestion("suggest_test", termSuggestionBuilder);
		searchSourceBuilder.suggest(suggestBuilder);

		searchSourceBuilder.profile(true);

		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices(indexName);
		searchRequest.source(searchSourceBuilder);

		try {
			// 同步
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

			// 异步

			return parseSearchResponse(searchResponse);
		} catch (IOException e) {
			log.info("操作异常", e);
		}

		return null;
	}

	private List<Map<String, Object>> parseSearchResponse(SearchResponse searchResponse) {
		SearchHits hits = searchResponse.getHits();
		TotalHits totalHits = hits.getTotalHits();
		int totalShards = searchResponse.getTotalShards();
		int successfulShards = searchResponse.getSuccessfulShards();
		int failedShards = searchResponse.getFailedShards();
		long numHits = totalHits.value;
		TotalHits.Relation relation = totalHits.relation;
		float maxScore = hits.getMaxScore();
		SearchHit[] searchHits = hits.getHits();

		Suggest suggest = searchResponse.getSuggest();
		if(suggest != null) {
			TermSuggestion termSuggestion = suggest.getSuggestion("suggest_test");
			for(TermSuggestion.Entry suggestion : termSuggestion.getEntries()) {
				for(TermSuggestion.Entry.Option option : suggestion) {
					String suggestText = option.getText().string();
					log.info("suggestTest is " + suggestText);
				}
			}
		}

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
	}

	@Override
	public void buildIndexRequestWithString(String indexName, String document) {
		IndexRequest request = new IndexRequest(indexName);
		request.id(document);
		String jsonString = "{\"id\":5,\"username\":\"daniel\",\"level\":1,\"birthday\":" + getBirthDay("1979-03-12") + "}";
		request.source(jsonString, XContentType.JSON);

		try {
			IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			log.info("操作异常", e);
		}
	}

	@Override
	public void buildIndexRequestWithMap(String indexName, String document) {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("id", 6);
		jsonMap.put("username", "kevin");
		jsonMap.put("level", 6);
		jsonMap.put("birthday", getBirthDay("1970-03-12"));
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
				builder.field("birthday", getBirthDay("1989-03-15"));
			}
			builder.endObject();
			IndexRequest request = new IndexRequest(indexName).id(document)
					.source(builder);

			ActionListener listener = giveListener();
			client.indexAsync(request, RequestOptions.DEFAULT, listener);

		} catch (IOException e) {
			log.info("操作异常", e);
		}
	}

	@Override
	public void buildIndexRequestWithKV(String indexName, String document) {
		IndexRequest request = new IndexRequest(indexName).id(document)
				.source("id", 8, "username", "cindy", "level", 3, "birthday", getBirthDay("1972-03-12"));

		/*request.routing("routing");

		request.timeout(TimeValue.timeValueSeconds(1));
		request.timeout("1s");

		request.version(2);
		request.versionType(VersionType.INTERNAL);

		request.opType(DocWriteRequest.OpType.CREATE);
		request.opType("create");

		request.setPipeline("pipeline");*/

		try {
			IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			log.info("操作异常", e);
		}
	}

	@Override
	public void term(String indexName, String document, String field) {
		TermVectorsRequest request = new TermVectorsRequest(indexName, document);
		request.setFields(field);
		try {
			TermVectorsResponse response = client.termvectors(request, RequestOptions.DEFAULT);
			processTermVectorsResponse(response);
		} catch (IOException e) {
			log.info("操作异常", e);
		}
	}

	private void processTermVectorsResponse(TermVectorsResponse response) {
		String index = response.getIndex();
		String id = response.getId();
		boolean found = response.getFound();
		log.info("term document, index:{}, id:{}, found:{}", index, id, found);

		List<TermVector> list = response.getTermVectorsList();
		for(TermVector termVector : list) {
			String fieldName = termVector.getFieldName();
			int docCount = termVector.getFieldStatistics().getDocCount();
			long sumTotalTermFreq = termVector.getFieldStatistics().getSumTotalTermFreq();
			long sumDocFreq = termVector.getFieldStatistics().getSumDocFreq();
			log.info("fieldName:{}, docCount:{}, sumTotalTermFreq:{}, sumDocFreq:{}", fieldName, docCount, sumTotalTermFreq, sumDocFreq);

			if(CollectionUtils.isNotEmpty(termVector.getTerms())) {
				for(TermVector.Term term : termVector.getTerms()) {
					String termStr = term.getTerm();
					Integer termFreq = term.getTermFreq();
					Integer docFreq = term.getDocFreq();
					Long totalTermFreq = term.getTotalTermFreq();
					Float score = term.getScore();

					log.info("termStr:{}, termFreq:{}, docFreq:{}, totalTermFreq:{}, score:{}", termStr, termFreq, docFreq, totalTermFreq, score);

					if(CollectionUtils.isNotEmpty(term.getTokens())) {
						for(TermVector.Token token : term.getTokens()) {
							Integer position = token.getPosition();
							Integer startOffset = token.getStartOffset();
							Integer endOffset = token.getEndOffset();
							String payLoad = token.getPayload();
							log.info("position:{}, startOffset:{}, endOffset:{}, payLoad:{}", position, startOffset, endOffset, payLoad);
						}
					}
				}
			}
		}
	}
}
