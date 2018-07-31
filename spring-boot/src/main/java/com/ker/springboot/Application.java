package com.ker.springboot;

import com.ker.springboot.zip.ZipDaoJPA;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
@Log4j2
@SpringBootApplication // initializes application context, enables auto-configuration, scans the package and sub-packages that it is defined in
// Where does Spring search for Beans? -? Spring automatically checks the package (i.e. com.ker.springboot) and the sub-packages for components (i.e. component scan)
@ComponentScan(value = {"com.ker.springboot"}) // explicitly specifying packages to search for components
@PropertySource("classpath:app.properties")     // application.properties is automatically recognised by Spring Boot
public class Application implements CommandLineRunner {

  @Autowired
  ZipDaoJPA zipDaoJPA;

  public static void main(String[] args) {
    System.out.println("Before app start...");
    ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    log.info("App context loaded!");
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Will be invoked as soon as the application is launched. Since the method is not static, can autowire beans.");

    log.debug("Info for all zips : {}", zipDaoJPA.getDataForAllZips());
    zipDaoJPA.addZip(4545, "blha");
    zipDaoJPA.deleteZip(4545);
    zipDaoJPA.updateZipData(97233, "Gresham edited");
    log.debug("Info for all zips post edits : {}", zipDaoJPA.getDataForAllZips());
  }
}
