package com.ker.springboot.Sort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class SelectionSort implements SortingAlgorithm{
    private static final Logger LOGGER = LogManager.getLogger(SelectionSort.class);

    public void sortNumbers(int arr[]) {
        LOGGER.debug("Using SelectionSort to sort...");
    int n = arr.length;

    // One by one move boundary of unsorted subarray
    for (int i = 0; i < n - 1; i++) {
      // Find the minimum element in unsorted array
      int min_idx = i;
      for (int j = i + 1; j < n; j++) if (arr[j] < arr[min_idx]) min_idx = j;

      // Swap the found minimum element with the first
      // element
      int temp = arr[min_idx];
      arr[min_idx] = arr[i];
      arr[i] = temp;
    }
  }
}
