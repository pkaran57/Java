package LanguageCore.oopdemo;

/* No support for multiple inheritance:

You can only specify one superclass for any subclass that you create. Java does not
support the inheritance of multiple superclasses into a single subclass. You can, as stated,
create a hierarchy of inheritance in which a subclass becomes a superclass of another
subclass. However, no class can be a superclass of itself.
 */

//Terminology:
//Employee is called base class
//Employee is called super class and CEO is called sub class of class Employee
public class CEO extends Employee{

    int salary;

    //imp: Use of super

    public CEO(int salary, String name){

        //the constructors are executed in order of derivation.
        //If super( ) is not used, then the default or parameterless constructor of each superclass will be executed

        super(name);            //super must be first line in constructor, super here is used to call superclass's constructor
        this.salary = salary;
    }

    //*********************************************TOPIC : use of super


    //*********************************************TOPIC : member hiding and overriding

    //this method hides method in superclass
    public void getObjectType(){

        //super is used to access hidden members of superclass
        //super.getObjectType();
        System.out.println("Object is of type CEO");
    }

    //this var hides var in superclass
    public int hiddenVar = 6;

    //*********************************************TOPIC : member hiding  and overriding

    //demo method
    public void exclusiveTOCEO() {
        System.out.println("This method is exclusive to CEO class");
    }
}
