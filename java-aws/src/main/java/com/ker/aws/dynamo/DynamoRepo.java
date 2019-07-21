package com.ker.aws.dynamo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Repository
class DynamoRepo {

    private final DynamoDbAsyncClient asyncClient;

    public DynamoRepo(final DynamoDbAsyncClient asyncClient) {
        this.asyncClient = asyncClient;
    }

    public void createTable(final String tableName) throws InterruptedException, ExecutionException {
        if(!tableExist(tableName)) {
            final CreateTableRequest request = CreateTableRequest.builder()
                    // The names and types of all primary and index key attributes only
                    .attributeDefinitions(
                            AttributeDefinition.builder()
                                    .attributeName("Gender")
                                    .attributeType(ScalarAttributeType.S)
                                    .build(),
                            AttributeDefinition.builder()
                                    .attributeName("Name")
                                    .attributeType(ScalarAttributeType.S)
                                    .build())
                    // The type of of schema.  Must start with a HASH type, with an optional second RANGE. HASH - partition key, RANGE - sort key
                    .keySchema(
                            KeySchemaElement.builder()
                                    .attributeName("Gender")
                                    .keyType(KeyType.HASH)
                                    .build(),
                            KeySchemaElement.builder()
                                    .attributeName("Name")
                                    .keyType(KeyType.RANGE)
                                    .build())
                    .billingMode(BillingMode.PAY_PER_REQUEST)
                    .tableName(tableName)
                    .build();

            final CreateTableResponse tableResponse = asyncClient.createTable(request).get();
            log.info("Create table response for '{}' table - {}", tableName, tableResponse);
        } else {
            log.info("Table with name '{}' already exists.", tableName);
        }
    }

    public void listAllTables() throws ExecutionException, InterruptedException {
        final CompletableFuture<ListTablesResponse> listTablesResponseCompletableFuture = asyncClient.listTables(ListTablesRequest.builder().build());
        log.info("All tables in DB - {}", listTablesResponseCompletableFuture.get());
    }

    public void deleteTable(final String tableName) throws ExecutionException, InterruptedException {
        if(tableExist(tableName)) {
            log.info("Delete '{}' table response - {}", tableName, asyncClient.deleteTable(DeleteTableRequest.builder().tableName(tableName).build()).get());
        } else {
            log.info("Cannot delete table '{}' since it does not exist", tableName);
        }
    }

    public boolean tableExist(String tableName) throws InterruptedException, ExecutionException {
        return tableExist(tableName, null);
    }

    private boolean tableExist(String tableName, String lastEvaluatedTableName) throws InterruptedException, ExecutionException {
        return asyncClient.listTables(ListTablesRequest.builder().exclusiveStartTableName(lastEvaluatedTableName).build()).thenApply(response -> {
            final boolean tableExist = response.tableNames().contains(tableName);

            if(tableExist) {
                return true;
            } else {
                if(!StringUtils.isEmpty(response.lastEvaluatedTableName())) {
                    try {
                        return tableExist(tableName, lastEvaluatedTableName);
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException("Encountered an error while determining if a table exist or not", e);
                    }
                } else {
                    return false;
                }
            }
        }).get();
    }
}
