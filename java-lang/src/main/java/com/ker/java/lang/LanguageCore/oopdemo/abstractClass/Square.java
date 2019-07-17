package com.ker.java.lang.LanguageCore.oopdemo.abstractClass;

//When an abstract class is subclassed, the subclass usually provides implementations for all of the abstract methods in its
// parent class. However, if it does not, then the subclass must also be declared abstract.

/*
imp: Declaring a class as final implicitly declares all of its methods as final (i.e. they can't be overridden). Final classes cannot be extended by any other class !
As you might expect, it is illegal to declare a class as both abstract and final since
an abstract class is incomplete by itself and relies upon its subclasses to provide complete
implementations.
 */
public final class Square extends Shape{

    public Square(String shapeDescription, int sideLength){
        super(shapeDescription);
        this.sideLength = sideLength;
    }

    private int sideLength;

    //area is an abstract method in subclass Shape
    public double area(){
        return sideLength * sideLength;
    }   //imp: this method cannot be protected, package private or private since access is being weakened

    public void thisCanBeOverRidden(){
        System.out.println("From Square");
    }
}