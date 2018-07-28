package com.ker.spring;

import com.ker.spring.Sort.SortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan({"com.ker.spring.Util", "com.ker.spring.Sort"})   // not needed for XML based configurations, only needed when using java based Spring annotations
public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String[] args){
        // ApplicationContext.xml should be on the classpath. Files in resources are in the classpath by default
        try(ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml")){  // auto closable
            LOGGER.info("Beans loaded by the XML context -> {}", (Object) applicationContext.getBeanDefinitionNames());

            SortingService sortingService = applicationContext.getBean(SortingService.class);

            sortingService.sort(new int[]{34,99,1,0,-43,3872732});
            sortingService.sort(new int[]{34,99,1,0,-43,3872732});   // Note that a new instance of bubblesort will be autowired even for the same instance of sortingService

            SortingService sortingService1 = applicationContext.getBean(SortingService.class);
            sortingService.sort(new int[]{34,99,1,0,-43,3872732});

            LOGGER.info("sortingService = " + sortingService);
            LOGGER.info("sortingService1 = " + sortingService1);
        }
    }
}
