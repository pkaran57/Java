package com.ker.aws.elasticsearch;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.elasticsearch.ElasticsearchAsyncClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class ElasticSearch {

    private static final AWSCredentialsProvider credentialsProvider = new DefaultAWSCredentialsProviderChain();

    private final ElasticsearchAsyncClient elasticSearchAsyncClient;

    public ElasticSearch(ElasticsearchAsyncClient elasticSearchAsyncClient) {
        this.elasticSearchAsyncClient = elasticSearchAsyncClient;
    }

    public void demo() throws ExecutionException, InterruptedException, IOException {
        //final CompletableFuture<CreateElasticsearchDomainResponse> cost = elasticSearchAsyncClient.createElasticsearchDomain(request -> request.domainName("cost").elasticsearchVersion("6.3"));
        //System.out.println(cost.get());

        final RestHighLevelClient restHighLevelClient = esClient("http://localhost:4578");


        CreateIndexRequest request = new CreateIndexRequest("encounters");

        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 1)
        );


        final HashMap<String, Map<String, String>> map = new HashMap<>();

        map.put("auto", Map.of("type", "text"));
        map.put("client_code", Map.of("type", "text"));
        map.put("language_id", Map.of("type", "text"));
        map.put("auto_key", Map.of("type", "text"));
        map.put("synonyms", Map.of("type", "text"));
        map.put("cpt_codes", Map.of("type", "text"));
        map.put("non_blue_ind", Map.of("type", "text"));

        request.mapping(Map.of("properties", map));

        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    public static RestHighLevelClient esClient(String aesEndpoint) {

        final String serviceName = "es";

        final AWS4Signer signer = new AWS4Signer();
        signer.setServiceName(serviceName);
        signer.setRegionName(System.getenv("AWS_REGION"));

        final HttpRequestInterceptor interceptor = new AWSRequestSigningApacheInterceptor(serviceName, signer, credentialsProvider);

        return new RestHighLevelClient(RestClient.builder(HttpHost.create(aesEndpoint)).setHttpClientConfigCallback(hacb -> hacb.addInterceptorLast(interceptor)));
    }
}
