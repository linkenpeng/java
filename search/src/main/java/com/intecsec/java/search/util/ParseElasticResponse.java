package com.intecsec.java.search.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.term.TermSuggestion;

import java.util.List;
import java.util.Map;

/**
 * @author Peter.Peng
 * @date 2021/3/29
 */
@Slf4j
public class ParseElasticResponse {

	public static void processGetResponse(GetResponse getResponse) {
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

	public static void processBulkByScrollResponse(BulkByScrollResponse response) {
		TimeValue timeValue = response.getTook();
		log.info("time is {}", timeValue.getMillis());
		long totalDocs = response.getTotal();
		long updatedDocs = response.getUpdated();
		log.info("totalDocs:{}, updatedDocs:{}", totalDocs, updatedDocs);
	}

	public static void processUpdateResponse(UpdateResponse response) {
		String index = response.getIndex();
		String id = response.getId();
		Long version = response.getVersion();
		DocWriteResponse.Result result = response.getResult();
		log.info("update document, index:{}, id:{}, version:{}", index, id, version);

		if(result == DocWriteResponse.Result.CREATED) {
			log.info("doc create");
		} else if(result == DocWriteResponse.Result.UPDATED) {
			log.info("doc update" + result.toString());
		} else if(result == DocWriteResponse.Result.DELETED) {
			log.info("doc delete " + result.toString());
		} else if(result == DocWriteResponse.Result.NOOP) {
			log.info("no doc");
		}
	}

	public static List<Map<String, Object>> parseSearchResponse(SearchResponse searchResponse) {
		if(searchResponse == null) {
			log.info("searchResponse is null.");
			return null;
		}

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
			log.info("index:{}, id:{}, score:{}", index, id, score);
			Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();
			log.info("highlightFieldMap:{}", highlightFieldMap);
			Map<String, Object> stringObjectMap = hit.getSourceAsMap();
			log.info("stringObjectMap:{}", stringObjectMap);
			list.add(stringObjectMap);
		}

		return list;
	}
}
