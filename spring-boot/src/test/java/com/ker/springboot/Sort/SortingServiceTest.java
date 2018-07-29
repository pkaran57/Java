package com.ker.springboot.Sort;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertArrayEquals;

@RunWith(MockitoJUnitRunner.class)
public class SortingServiceTest {

  @Spy
  private SortingAlgorithm sortingAlgorithm = new BubbleSort();

  @InjectMocks    // injects mocks and spies
  private SortingService sortingService;

  @Before
  public void setUp() throws Exception {
    System.out.println("@Before method will be called BEFORE every test!");
  }

  @After
  public void tearDown() throws Exception {
    System.out.println("@After method will be called AFTER every test!");
  }

  @BeforeClass
  public static void beforeClass(){
    System.out.println("@BeforeClass method will be called once per test class! We can do things like initializing DB connection / loading test data");
  }

  @AfterClass
  public static void afterClass(){
    System.out.println("@AfterClass method will be called once per test class!");
  }

  @Test
  public void sort() {
    int[] arrayToSort = new int[]{343, -454, 0, -1};
    int[] expectedSortedArray = new int[]{-454, -1, 0, 343};
    sortingService.sort(arrayToSort);
    assertArrayEquals(expectedSortedArray, arrayToSort);
  }
}