package com.ker.springboot;

import com.ker.springboot.Sort.SortingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/*
What are the beans?
Classes market with @Component

What are the dependencies for a bean?
fields marked with @Autowired annotation
 */
@SpringBootApplication
// Where does Spring search for Beans?
// Spring automatically checks the package (i.e. com.ker.springboot) and the sub-packages for components (i.e. component scan)
@ComponentScan(value = {"com.ker.springboot"})    // explicitly specifying packages to search for components
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		SortingService sortingService = applicationContext.getBean(SortingService.class);
		sortingService.sort(new int[]{34,99,1,0,-43,3872732});
		sortingService.sort(new int[]{34,99,1,0,-43,3872732});   // Note that a new instance of bubblesort will be autowired even for the same instance of sortingService

		SortingService sortingService1 = applicationContext.getBean(SortingService.class);
		sortingService.sort(new int[]{34,99,1,0,-43,3872732});

    System.out.println("sortingService = " + sortingService);
    System.out.println("sortingService1 = " + sortingService1);
	}
}
