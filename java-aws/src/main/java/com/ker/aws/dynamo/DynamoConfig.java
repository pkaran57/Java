package com.ker.aws.dynamo;

import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@org.springframework.context.annotation.Configuration
class DynamoConfig {

    /**
     * Client Lifecycle - Service clients in the SDK are thread-safe. For best performance, treat them as long-lived objects. Each client has its own connection pool resource
     * that is released when the client is garbage collected. The clients in the AWS SDK for Java 2.0 now extend the AutoClosable interface. For best practices, explicitly close
     * a client by calling the close method.
     *
     * NOTE: Spring will call DynamoDbAsyncClient.close() for us automatically!
     */
    @Bean
    public DynamoDbAsyncClient asyncDynamoClient() {
        return DynamoDbAsyncClient.builder()
                .endpointOverride(URI.create("http://localhost:8000"))
                .region(Region.US_WEST_2)
                .build();
    }

    /**
     * Spring will call DynamoDbClient.close() to cleanup for us
     */
    @Bean
    public DynamoDbClient dynamoClient() {
        return DynamoDbClient.create();   //  It uses the default provider chain to load credentials and the AWS Region.
    }
}
