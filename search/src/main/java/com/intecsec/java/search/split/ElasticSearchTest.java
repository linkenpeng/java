package com.intecsec.java.search.split;

import org.elasticsearch.common.settings.Settings;

/**
 * @author Peter.Peng
 * @date 2021/3/17
 */
public class ElasticSearchTest {

	public static void main(String[] args) {
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
	}
}
