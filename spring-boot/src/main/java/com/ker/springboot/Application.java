package com.ker.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/*
What are the beans?
Classes market with @Component

What are the dependencies for a bean?
fields marked with @Autowired annotation
 */
@SpringBootApplication // initializes application context, enables auto-configuration, scans the package and sub-packages that it is defined in
// Where does Spring search for Beans? -? Spring automatically checks the package (i.e. com.ker.springboot) and the sub-packages for components (i.e. component scan)
@ComponentScan(value = {"com.ker.springboot"}) // explicitly specifying packages to search for components
@PropertySource("classpath:app.properties")     // application.properties is automatically recognised by Spring Boot
public class Application {

  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
  }
}
