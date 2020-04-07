package com.ker.aws;

import com.ker.aws.cloudsearch.CloudSearch;
import com.ker.aws.dynamo.Dynamo;
import com.ker.aws.elasticsearch.ElasticSearch;
import com.ker.aws.s3.S3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private Dynamo dynamo;

    @Autowired
    private S3 s3;

    @Autowired
    private CloudSearch cloudSearch;

    @Autowired
    private ElasticSearch elasticSearch;

  /**
   * AWS SDK 2.0 doc - https://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/welcome.html
   * Set Up AWS Credentials and Region for Development - https://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/setup-credentials.html
   * Working with AWS Credentials - https://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/credentials.html
   * AWS Region selection - https://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/java-dg-region-selection.html
   */
  public static void main(String[] args) {
      System.setProperty("aws.accessKeyId", "test");
      System.setProperty("aws.secretAccessKey", "test");
      System.setProperty("aws.secretKey", "test");


      SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        elasticSearch.demo();
    }
}
