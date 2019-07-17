package com.ker.java.lang.LanguageCore.oopdemo.interfaces;

//Your class can implement more than one interface, so the implements keyword is followed by a comma-separated list of the
// interfaces implemented by the class. imp: By convention, the implements clause follows the extends clause, if there is one.
//imp: If a class does not implement all methods defined by an interface, then it must be declared abstract
public class Atorvastatin implements GenericDrug {

    //productDescription below hides productDescription in interface
    private String productDescription = "This generic drug helps keep cholesterol under control.";
    private int price = 100;

    public int getProductPrice() {
        return price;
    }

    @Override
    public void printInfo(){
        System.out.println("(From Atorvastatin.java) This product is " + productDescription);    //uses Atorvastatin class's productDescription var

        //imp: using super to access members of GenericDrug Interface
        GenericDrug.super.printInfo();
    }
    // cannot assign weaker access, i.e. all abstract methods in interface must be public in implementing class
    public boolean isCostlier(GenericDrug otherDrug){
        return this.price > otherDrug.getProductPrice();
    }
}
