package com.ker.spring;

import com.ker.spring.Sort.SortingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertArrayEquals;

//Load the context
@RunWith(SpringRunner.class)  // tells JUnit to run using Spring’s testing support. SpringRunner is the new name for SpringJUnit4ClassRunner, it’s just a bit easier on the eye.
@ContextConfiguration(locations="/testContext.xml")
public class ApplicationTest {

  @Autowired
  SortingService sortingService;

  @Test
  public void contextLoads() {
    int[] arrayToSort = new int[]{343, -454, 0, -1};
    int[] expectedSortedArray = new int[]{-454, -1, 0, 343};
    sortingService.sort(arrayToSort);
    assertArrayEquals(expectedSortedArray, arrayToSort);
  }
}