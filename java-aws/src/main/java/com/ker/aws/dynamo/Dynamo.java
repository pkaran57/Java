package com.ker.aws.dynamo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class Dynamo {

    private static final String TABLE_NAME = "Contacts";

    @Autowired
    private DynamoRepo dynamoRepo;

    public void demo() throws ExecutionException, InterruptedException {
        normalOperations();
    }

    private void normalOperations() throws ExecutionException, InterruptedException {
        dynamoRepo.deleteTable(TABLE_NAME);
        dynamoRepo.createTable(TABLE_NAME);
        dynamoRepo.describeTable(TABLE_NAME);
        dynamoRepo.listAllTables();
        dynamoRepo.deleteTable(TABLE_NAME);
    }

    /**
     * The purpose of this method is to test the 'lastEvaluatedTableName' on list table request
     */
    private void createAndList100Tables() throws ExecutionException, InterruptedException {
        for(int i = 0; i < 101; i++) {
            dynamoRepo.createTable(RandomStringUtils.randomAlphabetic(5, 50));
        }
        log.info("Done creating tables!");
        log.info("Table exists - {}", dynamoRepo.tableExist("does not"));
    }
}
