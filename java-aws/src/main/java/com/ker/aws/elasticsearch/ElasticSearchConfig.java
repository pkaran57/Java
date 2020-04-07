package com.ker.aws.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.elasticsearch.ElasticsearchAsyncClient;

import java.net.URI;

@Configuration
class ElasticSearchConfig {

    @Bean
    public ElasticsearchAsyncClient elasticSearchAsyncClient() {
        return ElasticsearchAsyncClient.builder()
                .endpointOverride(URI.create("http://localhost:4578"))
                .region(Region.US_WEST_2)
                .build();
    }

    @Bean
    public RestHighLevelClient elasticSearchClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }
}
