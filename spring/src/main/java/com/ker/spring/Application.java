package com.ker.spring;

import com.ker.spring.Sort.SortingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.ker.spring.Sort"})
public class Application {

    public static void main(String[] args){
        try(AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class)){  // auto closable
            SortingService sortingService = applicationContext.getBean(SortingService.class);

            sortingService.sort(new int[]{34,99,1,0,-43,3872732});
            sortingService.sort(new int[]{34,99,1,0,-43,3872732});   // Note that a new instance of bubblesort will be autowired even for the same instance of sortingService

            SortingService sortingService1 = applicationContext.getBean(SortingService.class);
            sortingService.sort(new int[]{34,99,1,0,-43,3872732});

            System.out.println("sortingService = " + sortingService);
            System.out.println("sortingService1 = " + sortingService1);
        }
    }
}
