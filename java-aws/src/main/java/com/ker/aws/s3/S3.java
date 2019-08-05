package com.ker.aws.s3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.*;

import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Component
public class S3 {

    private static final String TEST_BUCKET = "test-bucket";

    @Autowired
    private S3AsyncClient s3AsyncClient;

    public void demo() throws ExecutionException, InterruptedException {
        createBucket(TEST_BUCKET);
        putObject(TEST_BUCKET, "testKey", "testContent1");
        listObjects(TEST_BUCKET);
    }

    private void createBucket(final String bucketName) throws InterruptedException, ExecutionException {
        if(!bucketExists(bucketName)) {
            final CreateBucketResponse createBucketResponse = s3AsyncClient.createBucket(request -> request.bucket(bucketName)).get();
            log.info("Create bucket response - {}", createBucketResponse);
        } else {
            log.info("Bucket with name '{}' already exist.", bucketName);
        }
    }

    private boolean bucketExists(final String bucketName) throws InterruptedException, ExecutionException {
        return s3AsyncClient.listBuckets().thenApply(listBucketsResponse -> {
            final Set<String> bucketNames = listBucketsResponse.buckets().stream().map(Bucket::name).collect(Collectors.toSet());
            return bucketNames.contains(bucketName);
        }).get();
    }

    /**
     * Before you can delete an Amazon S3 bucket, you must ensure that the bucket is empty or the service will return an error. If you have a versioned bucket, you must also delete any versioned objects that are in the bucket.
     */
    private void deleteBucket(final String bucketName) throws ExecutionException, InterruptedException {
        deleteAllObjectsInBucket(bucketName);
        final CompletableFuture<DeleteBucketResponse> deleteBucketResponseCompletableFuture = s3AsyncClient.deleteBucket(request -> request.bucket(bucketName));
        log.info("Delete '{}' bucket status - {}", bucketName, deleteBucketResponseCompletableFuture.get());
    }

    private void deleteAllObjectsInBucket(final String bucketName) throws ExecutionException, InterruptedException {
        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder().bucket(bucketName).build();
        ListObjectsV2Response listObjectsV2Response;
        do {
            listObjectsV2Response = s3AsyncClient.listObjectsV2(listObjectsV2Request).get();
            for (S3Object s3Object : listObjectsV2Response.contents()) {
                final CompletableFuture<DeleteObjectResponse> deleteObjectResponseCompletableFuture = s3AsyncClient.deleteObject(request -> request.bucket(bucketName).key(s3Object.key()));
                log.info("Delete status for {} object from {} bucket - {}", s3Object.key(), bucketName, deleteObjectResponseCompletableFuture.get());
            }

            listObjectsV2Request = ListObjectsV2Request.builder().bucket(bucketName)
                                                        .continuationToken(listObjectsV2Response.nextContinuationToken())
                                                        .build();

        } while (listObjectsV2Response.isTruncated());
    }

    private void putObject(final String bucketName, final String objectKey,final String content) throws ExecutionException, InterruptedException {
        final PutObjectResponse putObjectResponse = s3AsyncClient.putObject(request -> request.bucket(bucketName).key(objectKey), AsyncRequestBody.fromString(content)).get();
        log.info("Put object request for {} key in {} bucket - {}", objectKey, bucketName, putObjectResponse);
    }

    private void listObjects(final String bucketName) throws ExecutionException, InterruptedException {
        log.info("Listing all objects in {} bucket", bucketName);
        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder().bucket(bucketName).build();
        ListObjectsV2Response listObjectsV2Response;
        do {
            listObjectsV2Response = s3AsyncClient.listObjectsV2(listObjectsV2Request).get();
            for (S3Object s3Object : listObjectsV2Response.contents()) {
                final ResponseBytes<GetObjectResponse> getObjectResponseResponseBytes = s3AsyncClient.getObject(request -> request.bucket(bucketName).key(s3Object.key()), AsyncResponseTransformer.toBytes()).get();
                log.info("Object key - {}, object value - {}", s3Object.key(), getObjectResponseResponseBytes.asString(Charset.defaultCharset()));
            }

            listObjectsV2Request = ListObjectsV2Request.builder().bucket(bucketName)
                    .continuationToken(listObjectsV2Response.nextContinuationToken())
                    .build();

        } while (listObjectsV2Response.isTruncated());
    }
}
