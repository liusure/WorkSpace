package com.saas.common.es;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {

	@Value("${elasticsearch.node-url-list}")
	private String nodeUrlList;// 127.0.0.1:9500,127.0.0.1:9200

	/**
	 * LowLevelRestConfig
	 *
	 * @param
	 * @return org.elasticsearch.client.RestClient
	 */
	@Bean
	public RestClient restClient() {
		String[] split = nodeUrlList.split(",");
		HttpHost[] httpHostArray = new HttpHost[split.length];
		for (int i = 0; i < split.length; i++) {
			String item = split[i];
			httpHostArray[i] = new HttpHost(item.split(":")[0], Integer.parseInt(item.split(":")[1]), "http");
		}
		RestClientBuilder clientBuilder = RestClient.builder(httpHostArray);
		// 设置Header编码
		Header[] defaultHeaders = { new BasicHeader("content-type", "application/json") };
		clientBuilder.setDefaultHeaders(defaultHeaders);
		// 添加其他配置，这些配置都是可选的
		return clientBuilder.build();
	}

	/**
	 * HighLevelRestConfig
	 *
	 * @param
	 * @return org.elasticsearch.client.RestClient
	 */
	@Bean
	public RestHighLevelClient restHighLevelClient() {
		String[] split = nodeUrlList.split(",");
		HttpHost[] httpHostArray = new HttpHost[split.length];
		for (int i = 0; i < split.length; i++) {
			String item = split[i];
			httpHostArray[i] = new HttpHost(item.split(":")[0], Integer.parseInt(item.split(":")[1]), "http");
		}
		return new RestHighLevelClient(RestClient.builder(httpHostArray));
	}
}
