package com.ker.springboot.Sort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

// by specifying proxyMode, the proxy is used to generate instance of the bean whenever a component auto-wires the bean
// note that the proxy is autowired and not the bean itself, the autowired proxy will return a new instance of the bubble sort instance
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode=ScopedProxyMode.TARGET_CLASS)
@Service
public class BubbleSort implements SortingAlgorithm {
  private static final Logger LOGGER = LogManager.getLogger(BubbleSort.class);

  public void sortNumbers(int arr[]) {
    LOGGER.debug("Using BubbleSort to sort...");

    int n = arr.length;
    for (int i = 0; i < n - 1; i++)
      for (int j = 0; j < n - i - 1; j++)
        if (arr[j] > arr[j + 1]) {
          // swap temp and arr[i]
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
  }
}
