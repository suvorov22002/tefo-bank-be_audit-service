package com.pkf.cbs.historizationservice.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConnection {

    @Value("${elasticsearch.url}")
    private String elasticSearchURL;
    @Value("${elasticsearch.port}")
    private int elasticSearchPort;
    @Value("${elasticsearch.protocol}")
    private String elasticSearchScheme;
    @Bean
    public RestHighLevelClient makeConnection() {

        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(elasticSearchURL, elasticSearchPort, elasticSearchScheme)));
    }

}
