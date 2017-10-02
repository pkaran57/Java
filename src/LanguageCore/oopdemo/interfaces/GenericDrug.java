package LanguageCore.oopdemo.interfaces;

/*
Why use interfaces ?

There are a number of situations in software engineering when it is important for disparate groups of programmers to agree to
a "contract" that spells out how their software interacts. Each group should be able to write their code without any knowledge
 of how the other group's code is written. Generally speaking, interfaces are such contracts.

For example, imagine a futuristic society where computer-controlled robotic cars transport passengers through city streets
without a human operator. Automobile manufacturers write software (Java, of course) that operates the automobile—stop, start,
 accelerate, turn left, and so forth. Another industrial group, electronic guidance instrument manufacturers, make computer
 systems that receive GPS (Global Positioning System) position data and wireless transmission of traffic conditions and use
 that information to drive the car.

The auto manufacturers must publish an industry-standard interface that spells out in detail what methods can be invoked
to make the car move (any car, from any manufacturer). The guidance manufacturers can then write software that invokes the
methods described in the interface to command the car. Neither industrial group needs to know how the other group's software
is implemented. In fact, each group considers its software highly proprietary and reserves the right to modify it at any time,
 as long as it continues to adhere to the published interface

Through the use of the interface keyword, Java allows you to fully abstract an interface from its implementation.
Although they are similar to abstract classes, interfaces
have an additional capability: A class can implement more than one interface. By contrast, a
class can only inherit a single superclass (abstract or otherwise).
*/

//A class can implement more than one interface.
//imp:an interface can either be public or no access modifier (package default)
//Interfaces cannot be instantiated—they can only be implemented by classes or extended by other interfaces.
//imp:an interface can extend more than 1 interface !
//imp:members of an interface are public and can only be public. Thus, no need to mention public access modifier
//imp:static blocks and constructors are not allowed in an interface
public interface GenericDrug {

    //imp: all variables in an interface are public, static and final by default !
    String productDescription = "a generic drug";

    //imp: all methods in an interface are public by default. Following are 3 types of method an interface can have :

    //imp: method with default implementation
    default void printInfo(){
        System.out.println("(From GenericDrug.java) This product is " + productDescription);
    }

    //static method, need not be implemented by implementing class
    //can be accessed as follows : GenericDrug.staticMethod();
    static void staticMethod(){
        System.out.println("(From GenericDrug.java) This product is " + productDescription);
    }

    //method with no implementation, any class implementing this interface will have to implement it !
    int getProductPrice();

    boolean isCostlier(GenericDrug otherDrug);
}
