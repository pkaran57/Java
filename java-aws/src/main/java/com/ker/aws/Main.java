package com.ker.aws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;

@Slf4j
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.setProperty("aws.accessKeyId", "");
        System.setProperty("aws.secretAccessKey", "");

        try(S3Client s3Client = S3Client.builder()
                .region(Region.US_WEST_2)
                .build()) {

            final String BUCKET_NAME = "ker-bucket";

            s3Client.listBuckets(ListBucketsRequest.builder().build());

            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder().bucket(BUCKET_NAME).build();
            CreateBucketResponse createBucketResponse = s3Client.createBucket(createBucketRequest);     // http://ker-bucket.s3.amazonaws.com/

            log.info(createBucketResponse.toString());
        }
    }
}
