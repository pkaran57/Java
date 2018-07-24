package com.ker.springboot.Sort;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

// This is a general-purpose stereotype annotation indicating that the class is a spring component
@Component
public class SortingService {

  // sorting algorithm is a dependency of SortingService. We let Spring know this by adding an autowired annotation
  // qualifier for identifying candidate beans when autowiring
  @Qualifier("bubbleSort")
  @Autowired
  private SortingAlgorithm sortingAlgorithm;

  public void sort(int[] numbers) {
    if (ArrayUtils.isEmpty(numbers)) return;
    try {
      System.out.println("Before sorting: ");
      printArray(numbers);
      sortingAlgorithm.sortNumbers(numbers);
      System.out.println("After sorting: ");
      printArray(numbers);
    } catch (Exception e) {
      System.err.println("Encountered following error sorting numbers: " + e);
      e.printStackTrace();
    }
  }

  public static void printArray(int arr[]) {
    int n = arr.length;
    for (int i = 0; i < n; ++i) System.out.print(arr[i] + " ");
    System.out.println();
  }
}
