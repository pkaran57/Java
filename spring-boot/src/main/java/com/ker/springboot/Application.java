package com.ker.springboot;

import com.ker.springboot.zip.Zip;
import com.ker.springboot.zip.ZipDaoJDBC;
import com.ker.springboot.zip.ZipDaoJPA;
import com.ker.springboot.zip.ZipDaoSpringJpa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
Imp: Two types of IOC:
1. Dependency Lookup - Obtaining bean from Application context
2. Dependency Injection (Preferred) - Example: @Autowire (filed injection), constructor or setter injection

Imp: Auto-configuration
Enable auto-configuration of the Spring Application Context, attempting to guess and configure beans that you are likely to need. Auto-configuration classes are usually
applied based on your classpath and what beans you have defined. For example, if you have tomcat-embedded.jar on your classpath you are likely to want a
TomcatServletWebServerFactory (unless you have defined your own ServletWebServerFactory bean). Auto-configuration is always applied after user-defined beans have been registered.
 */
@Log4j2
// Onenote: Auto-configuration
@SpringBootApplication // initializes application context, enables auto-configuration, scans the package and sub-packages that it is defined in
// Where does Spring search for Beans? Spring automatically checks the package (i.e. com.ker.springboot) and the sub-packages for components (i.e. component scan)
//@ComponentScan(value = {"com.ker.springboot"}) // explicitly specifying packages to search for components
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml"})     // application.properties is automatically recognised by Spring Boot
//@ImportResource(locations = {"classpath:spring/app-context-xml.xml"})   // Indicates one or more resources containing bean definitions to import.
@Configuration
public class Application implements CommandLineRunner {

/*
  Imp: 3 ways in which dependency can be injected: 1. Constructor injection, 2. Setter injection, 3. @Autowired
  In general, you should choose an injection type based on your use case. Setter-based injection allow dependencies to be swapped out without creating new objects and also lets your class
  choose appropriate defaults without the need to explicitly inject an object. Constructor injection is a good choice when you want to ensure that dependencies are being passed to a component
  and when designing for immutable objects.
*/

/*
  The field is private, but the Spring IoC container does not really care about that; it uses reflection to populate the required dependency.
  Imp: Cons of filed injection:
      • Although it is easy to add dependencies this way, we must be careful not to violate the single responsibility principle. Having more dependencies means more responsibilities for a class, which might lead to a difficulty of separating concerns at refactoring time. The situation when a class becomes bloated is easier to see when dependencies are set using constructors or setters but is quite well hidden when using field injection.
      • The responsibility of injecting dependencies is passed to the container in Spring, but the class should clearly communicate the type of dependencies needed using a public interface, through methods or constructors. Using field injections, it can become unclear what type of dependency is really needed and if the dependency is mandatory or not.
      • Field injection introduces a dependency of the Spring container, as the @Autowired annotation is a Spring component; thus, the bean is no longer a POJO and cannot be instantiated independently.
      • Imp: Field injection cannot be used for final fields. This type of fields can only be initialized using constructor injection.
      • Field injection introduces difficulties when writing tests as the dependencies have to be injected manually.
*/
  @Autowired
  private ZipDaoSpringJpa zipDaoSpringJpa;

  private ZipDaoJDBC zipDaoJDBC;
  private ZipDaoJPA zipDaoJPA;

/*
  constructor-based injection can be leveraged in @Configuration annotated classes and if such a class has only one constructor, then the @Autowired annotation can be omitted as well
  The @Autowired annotation can be applied to only one of the constructor methods. If we apply the annotation to more than one constructor method, Spring will complain while bootstrapping ApplicationContext.
*/
  public Application(final ZipDaoJPA zipDaoJPA) {
    this.zipDaoJPA = zipDaoJPA;
  }

  @Autowired
  public void setZipDaoJDBC(ZipDaoJDBC zipDaoJDBC, @Qualifier("str1") String testStr, @Value("#{joe.age}") int testInt) {
    this.zipDaoJDBC = zipDaoJDBC;
    log.debug("Using Spel, testInt = {}", testInt);
  }

  @Bean
  public String str1(){
    return "str1";
  }

  @Bean
  public String str2(){
    return "str2";
  }

  @Bean
  public int intVal(){
    return 99;
  }

  public static void main(String[] args) {
    System.out.println("Before app start...");
    ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    String str2 = (String) applicationContext.getBean("str2");
    log.debug("Str2 = {}", str2);
    log.info("App context loaded!");
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Will be invoked as soon as the application is launched. Since the method is not static, can autowire beans.");

    zipDaoJDBC.addZip(23892, "randomLocation");

    log.debug("Info for all zips : {}", zipDaoJPA.getDataForAllZips());
    zipDaoJPA.addZip(4545, "blha");
    zipDaoJPA.deleteZip(4545);
    zipDaoJPA.updateZipData(97233, "Gresham edited");
    log.debug("Info for all zips post edits : {}", zipDaoJPA.getDataForAllZips());

    log.debug("Info for all zips : {}", zipDaoSpringJpa.findAll());
    zipDaoSpringJpa.save(Zip.builder().zip(4545).locationInfo("blha").build());
    zipDaoSpringJpa.deleteById(4545);
    zipDaoSpringJpa.save(Zip.builder().zip(97233).locationInfo("Gresham edited").build());
    log.debug("Info for all zips post edits : {}", zipDaoSpringJpa.findAll());
  }

  @Configuration
  class innerClassWithBeans {

    @Bean
    Person joe(){
      return new Person("joe", 99);
    }

    @Getter
    @AllArgsConstructor
    class Person {
      private String name;
      private int age;
    }

  }
}
