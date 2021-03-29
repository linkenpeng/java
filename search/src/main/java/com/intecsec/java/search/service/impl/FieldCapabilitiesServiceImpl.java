package com.intecsec.java.search.service.impl;

import com.intecsec.java.search.service.FieldCapabilitiesService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.fieldcaps.FieldCapabilities;
import org.elasticsearch.action.fieldcaps.FieldCapabilitiesRequest;
import org.elasticsearch.action.fieldcaps.FieldCapabilitiesResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Peter.Peng
 * @date 2021/3/29
 */
@Slf4j
@Service
public class FieldCapabilitiesServiceImpl implements FieldCapabilitiesService {

	@Autowired
	private RestHighLevelClient client;

	@Override
	public void doSearch(String field, List<String> indices) {
		FieldCapabilitiesRequest fieldCapabilitiesRequest = new FieldCapabilitiesRequest().fields(field)
				.indices(indices.get(0), indices.get(1));
		fieldCapabilitiesRequest.indicesOptions(IndicesOptions.lenientExpandOpen());

		try {
			FieldCapabilitiesResponse response = client.fieldCaps(fieldCapabilitiesRequest, RequestOptions.DEFAULT);
			proccessResponse(response, field, indices);
		} catch (IOException e) {
			log.info("处理异常", e);
		}
	}

	private void proccessResponse(FieldCapabilitiesResponse response, String field, List<String> indices) {
		Map<String, FieldCapabilities> fieldCapabilitiesMap = response.getField(field);
		Set<String> set = fieldCapabilitiesMap.keySet();
		FieldCapabilities fieldCapabilities = fieldCapabilitiesMap.get("text");

		boolean isSearchAble = fieldCapabilities.isSearchable();
		boolean isAggregatable = fieldCapabilities.isAggregatable();
		log.info("isSearchAble:{}, isAggregatable:{}", isSearchAble, isAggregatable);

		String[] indicesArray = fieldCapabilities.indices();
		if(indicesArray != null) {
			log.info("indicesArray length is ", indicesArray.length);
		}
		String[] nonSearchableIndices = fieldCapabilities.nonSearchableIndices();
		if(nonSearchableIndices != null) {
			log.info("nonSearchableIndices length is ", nonSearchableIndices.length);
		}
		String[] nonAggregatableIndices = fieldCapabilities.nonAggregatableIndices();
		if(nonAggregatableIndices != null) {
			log.info("indicesArray length is ", nonAggregatableIndices.length);
		}
	}
}
