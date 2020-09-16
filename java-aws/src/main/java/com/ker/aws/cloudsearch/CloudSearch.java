package com.ker.aws.cloudsearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.cloudsearch.CloudSearchAsyncClient;
import software.amazon.awssdk.services.cloudsearch.model.DescribeDomainsResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class CloudSearch {

    private final CloudSearchAsyncClient cloudSearchAsyncClient;

    public CloudSearch(CloudSearchAsyncClient cloudSearchAsyncClient) {
        this.cloudSearchAsyncClient = cloudSearchAsyncClient;
    }

    public CompletableFuture<DescribeDomainsResponse> getSearchDomainResponse() {
        return cloudSearchAsyncClient.describeDomains();
    }

    public void demo() throws ExecutionException, InterruptedException {
        final CompletableFuture<DescribeDomainsResponse> searchDomainResponse = getSearchDomainResponse();
        System.out.println(searchDomainResponse.get());
    }
}
