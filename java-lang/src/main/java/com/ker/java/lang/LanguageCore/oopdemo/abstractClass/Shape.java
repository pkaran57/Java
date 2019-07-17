package com.ker.java.lang.LanguageCore.oopdemo.abstractClass;

// imp: Abstract class
//An abstract class is a class that is declared abstract—it may or may not include abstract methods.
//Abstract classes cannot be instantiated, but they can be subclassed.
//abstract classes can have methods and fields just like any other normal class
public abstract class Shape {

    public Shape(){
        this("");
    }

    public Shape(String shapeDescription){
        this.shapeDescription = shapeDescription;
    }

    protected String shapeDescription;

    //imp: An abstract method is a method that is declared without an implementation, abstract methods cannot be private
    public abstract double area();

    public void printShapeDescription(){
        System.out.println(shapeDescription);
    }

    //imp: final methods cannot be overridden
    public final void thisClassCannotBeOverridden(){
        System.out.println("one way of using final is to prevent menthod overriding");
    }

    public void thisCanBeOverRidden(){
        System.out.println("From Shape");
    }
}