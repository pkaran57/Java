package com.ker.springboot;

import com.ker.springboot.sort.SortingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

//Load the context
@RunWith(SpringRunner.class)  // tells JUnit to run using Spring’s testing support. SpringRunner is the new name for SpringJUnit4ClassRunner, it’s just a bit easier on the eye.
@SpringBootTest  			  // is saying “bootstrap with Spring Boot’s support” (e.g. load application.properties and give me all the Spring Boot goodness)
@ContextConfiguration(classes=Application.class)
public class ApplicationTests {

	@Autowired
	private SortingService sortingService;

	@Test
	public void contextLoads() {
		int[] arrayToSort = new int[]{343, -454, 0, -1};
		int[] expectedSortedArray = new int[]{-454, -1, 0, 343};
		sortingService.sort(arrayToSort);
		assertArrayEquals(expectedSortedArray, arrayToSort);
	}

	@Test
	public void listTestEnvUrl() {
		assertEquals("http://dev-test.com", sortingService.listTestEnvUrl());
	}
}
