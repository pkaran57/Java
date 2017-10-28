package LanguageCore.oopdemo;

//in Java, all class objects must be dynamically allocated i.e. using new
public class Employee {

    private String name;
    private byte employeeTypeId = 1;

    //*********************************************TOPIC : constructors and this keyword

    /*The compiler automatically provides a no-argument, default constructor for any class without constructors.
    This default constructor will call the no-argument constructor of the superclass (make sure super class has a no arg constructor !).
    When you do not explicitly define a constructor for a class, then Java creates a default constructor for the class.
    The default constructor automatically initializes all instance(member) variables to their default values, which are
    zero, null, and false, for numeric types, reference types, and boolean, respectively.

    If a constructor with args is defined, then no default/ parameterless constructor is automatically provided. One needs to be explicitly defined.
    */
    public Employee(){

        //this can be used inside any method to refer to the current object
        this("DEFAULT NAME");
    }

    public Employee(String name){
        this.name = name;
        finalField = 32;
    }

    //When you declare a variable without assigning it an explicit value, the Java compiler will assign a default value.
    //imp: variables below are package private
    float ratio;        // default value: 0.0
    boolean success;    // default value: false

    String Name;        // default: null
    Object obj;         // default: null

    //*********************************************END OF TOPIC : constructors and this keyword


    //*********************************************TOPIC : static and final

    /*
    Some restrictions associated with static methods :
        1. They cannot refer to this or super
        2. They can only use static variables and call static method
     */

    //final field must be assigned a value either when declared or in constructor
    final int finalField;
    static final int finalStaticField;  // must be initialized in static block or while declaration

    static int statInt;

    //Onenote: OOP [Block initializers]
    //static block that gets executed exactly once, when the class is first loaded
    static {
        System.out.println("Static block initialized.");
        statInt = 99;
        finalStaticField = 234;
    }

    //a static method
    public static void printEmployeeID(){
        System.out.println("Employee ID is " + finalStaticField);
    }

    //*********************************************END OF TOPIC : static and final


    //imp: Nested class

    /*Why Use Nested Classes?

    Compelling reasons for using nested classes include the following:

    It is a way of logically grouping classes that are only used in one place: If a class is useful to only one other class, then it is
    logical to embed it in that class and keep the two together. Nesting such "helper classes" makes their package more streamlined.

    It increases encapsulation: Consider two top-level classes, A and B, where B needs access to members of A that would otherwise be
    declared private. By hiding class B within class A, A's members can be declared private and B can access them. In addition, B itself
    can be hidden from the outside world.

    It can lead to more readable and maintainable code: Nesting small classes within top-level classes places the code closer to where
    it is used.
    */

    //inner classes can have the same access modifiers as other members of class
    //NOTE : Top level classes cannot be declared static (Ex: Employee class cannot be static). Only nested classes can be static.
    //imp: Nested static classes can be instantiated / initialized. NOTE: Inner class is static as member of outer class. No such concept as static class
    public static class StaticInnerClass{
        public void print(){
            //can access static members of enclosing class
            System.out.println("finalStaticField from Employee class is " + finalStaticField);
            printEmployeeID();

            //cannot access non-static members
            //System.out.println("employeeTypeId from Employee class is " + employeeTypeId);

        }
        int test;
    }

    public String innerVar = "InnerVar in parent (Employee) class";

    //Non-static nested classes (inner classes) have access to other members of the enclosing class, even if they are declared private.
    public class InnerClass{

        public String innerVar = "InnerVar in InnerClass";

        public void print(){
            //can access static as well as non static members of enclosing class
            System.out.println("finalStaticField from Employee class is " + finalStaticField);
            //getObjectType();      //non-static method
            //printEmployeeID();      //static method
            System.out.println(innerVar);   // innerVar in Employee is hidden
            System.out.println(Employee.this.innerVar);
        }
    }

    //*********************************************END OF TOPIC : inner class


    //*********************************************TOPIC : variable length arguments

    //the variable-length parameter must be the last parameter declared by the method
    //There is one more restriction to be aware of: there must be only one varargs parameter.
    public void varLengthArgs(int j, String a, String... s){    //s is simply String[] s

        //s.length will be 0 in case no string is passed
        System.out.println("There are " + s.length + " strings passed as String...s");
    }

    /*
    Var args and ambiguity:

    public void varArg(int ... i)
    public void varArg(bool ... b)

    //this is okay
    varArg(1)
    varArg(true, false)

    //this will give compile time error !
    varArg()

    Consider:
    public void varArg(int i)
    public void varArg(int ... i)

    //this will give compilation error !
    varArg(1)
     */

    //*********************************************END OF TOPIC : variable length arguments

    //method below for demo purpose
    public void getObjectType(){

        System.out.println("Object is of type Employee");
    }

    //variable for demo purpose
    public int hiddenVar = 5;
}
