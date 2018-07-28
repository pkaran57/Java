package com.ker.springboot.Sort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // tells JUnit to run using Spring’s testing support. SpringRunner is the new name for SpringJUnit4ClassRunner, it’s just a bit easier on the eye.
@SpringBootTest  // is saying “bootstrap with Spring Boot’s support” (e.g. load application.properties and give me all the Spring Boot goodness)
public class SortingServiceTest {
  private static Logger LOGGER = LogManager.getLogger(SortingServiceTest.class);

  @Before
  public void setUp() throws Exception {
    LOGGER.warn("@Before method will be called BEFORE every test!");
  }

  @After
  public void tearDown() throws Exception {
    LOGGER.warn("@After method will be called AFTER every test!");
  }

  @BeforeClass
  public static void beforeClass(){
    LOGGER.fatal("@BeforeClass method will be called once per test class! We can do things like initializing DB connection / loading test data");
  }

  @AfterClass
  public static void afterClass(){
    LOGGER.fatal("@AfterClass method will be called once per test class!");
  }

  @Test
  public void sort() {
    int[] arrayToSort = new int[]{343, -454, 0, -1};
    int[] expectedSortedArray = new int[]{-454, -1, 0, 343};

    assertArrayEquals(expectedSortedArray, arrayToSort);
  }

  @Test
  public void listTestEnvUrl() {}
}