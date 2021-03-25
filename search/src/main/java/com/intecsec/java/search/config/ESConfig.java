package com.intecsec.java.search.config;

import org.apache.http.HttpHost;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Peter.Peng
 * @date 2021/3/24
 */
@Configuration
public class ESConfig {

	@Bean(destroyMethod = "close")
	public RestHighLevelClient client() {
		RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost", 9200, "http"))
				.setRequestConfigCallback(builder -> builder.setConnectTimeout(5000).setSocketTimeout(60000))
				.setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder
						.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(1)
						.build()));

		RestHighLevelClient restClient = new RestHighLevelClient(restClientBuilder);
		return restClient;
	}
}
