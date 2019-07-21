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
        listAllTablesMoreThan100();
    }

    private void normalOperations() throws ExecutionException, InterruptedException {
        dynamoRepo.deleteTable(TABLE_NAME);
        dynamoRepo.createTable(TABLE_NAME);
        dynamoRepo.listAllTables();
        dynamoRepo.deleteTable(TABLE_NAME);
    }

    private void listAllTablesMoreThan100() throws ExecutionException, InterruptedException {
        for(int i = 0; i < 150; i++) {
            dynamoRepo.createTable(RandomStringUtils.randomAlphabetic(5, 50));
        }
        dynamoRepo.listAllTables();
    }
}
