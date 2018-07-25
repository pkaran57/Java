package com.ker.springboot.Sort;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// This is a general-purpose stereotype annotation indicating that the class is a spring component
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)     // scope is singleton (one bean per application context) by default
public class SortingService {


  // 3 ways of resolving conflicts when multiple beans of same type exist: 1. @Qualifier, 2. @Primary, 3. name of the variable (i.e. variable named after desired implementing class)
  @Qualifier("bubbleSort")
  @Autowired
  // sorting algorithm is a dependency of SortingService. We let Spring know this by adding an autowired annotation
  private SortingAlgorithm selectionSort;      // If a bean has @Autowired without any @Qualifier, and multiple beans of the type exist, the candidate bean marked @Primary will be chosen,
                                               // i.e. it is the default selection when no other information is available, i.e. when @Qualifier is missing
                                               // Order of priority: @Qualifier, @Primary and name when it comes to conflict resolution

  @PostConstruct
  private void postConstructor(){
//    The PostConstruct annotation is used on a method that needs to be executed after dependency injection is done to perform any initialization. This method MUST be invoked before the class is
//  put into service. This annotation MUST be supported on all classes that support dependency injection. The method annotated with PostConstruct MUST be invoked even if the class does not request
// any resources to be injected. Only one method can be annotated with this annotation.
    System.out.println("Post constructor called...");
  }

  @PreDestroy
  private void preDestroy(){
//    The PreDestroy annotation is used on methods as a callback notification to signal that the instance is in the process of being removed by the container. The method annotated with PreDestroy
//    is typically used to release resources that it has been holding. This annotation MUST be supported by all container managed objects that support PostConstruct
//    except the application client container in Java EE 5.
    System.out.println("pre-destroy method called");
  }

  // no usages, putting it here to demonstrate that the constructor is not used by the spring framework
  public void SortingService(){
    System.out.println("Constructor called...");
  }

  public void sort(int[] numbers) {
    if (ArrayUtils.isEmpty(numbers)) return;
    try {
      System.out.println("Before sorting: ");
      printArray(numbers);
      selectionSort.sortNumbers(numbers);
      System.out.println("After sorting: ");
      printArray(numbers);
      System.out.println("SortingAlgorithm instance: " + selectionSort);
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
