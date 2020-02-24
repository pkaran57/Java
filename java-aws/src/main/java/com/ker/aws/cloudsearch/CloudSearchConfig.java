package com.ker.aws.cloudsearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudsearch.CloudSearchAsyncClient;

@Configuration
class CloudSearchConfig {

    @Bean
    public CloudSearchAsyncClient cloudSearchAsyncClient() {
        return CloudSearchAsyncClient.builder()
                .region(Region.US_WEST_2)
                .build();
    }
}
