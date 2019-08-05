package com.ker.aws.s3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;

import java.net.URI;

@Configuration
class S3Config {

    @Bean
    public S3AsyncClient asyncS3Client() {
        return S3AsyncClient.builder()
                .endpointOverride(URI.create("http://localhost:4572"))
                .region(Region.US_WEST_2)
                .build();
    }
}
