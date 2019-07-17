package com.ker.java.lang.LanguageCore;

/*enumeration is a list of named constants

in Java, an enumeration defines a class type. By making enumerations into classes, the capabilities of the enumeration
are greatly expanded. For example, in Java, an enumeration can have constructors, methods, and instance variables

Here are two restrictions that apply to enumerations. First, an enumeration can’t
inherit another class. Second, an enum cannot be a superclass. This means that an enum
can’t be extended. Otherwise, enum acts much like any other class type. The key is to
remember that each of the enumeration constants is an object of the class in which it is defined.

imp: All enumerations automatically inherit java.lang.Enum.This class defines several methods that are available for use by all enumerations*/
public class Enumerations {

    private enum Apple {

        // Jonathan, GoldenDel, RedDel, Winesap, Cortland are called enum constants
        //Each is implicitly declared as a public, static final member of Apple
        //each enumeration constant is an object of its enumeration type, Apple in this case
        Jonathan(10), GoldenDel(9), RedDel(12), Winesap(15), Cortland;     // good practice: upper case for (enum) constants

        private int price; // price of each apple

//        when you define a constructor for an enum, the constructor is called when each enumeration constant is created.
//        Also, each enumeration constant has its own copy of any instance variables defined by the enumeration.
        Apple() {price = -1;}
        Apple(int p) { price = p; }

        int getPrice() { return price; }
        void setPrice(int p) { price = p; }
    }

    public static void enumDemo(){

        Apple ap = Apple.Jonathan;
        Apple ap1 = Apple.Cortland;

        System.out.println(ap); // will print out Jonathan
        System.out.println(ap.getPrice());

        switch(ap){

//            Notice that in the case statements, the names of the enumeration constants are used without
//            being qualified by their enumeration type name. That is, Winesap, not Apple.Winesap, is
//            used. This is because the type of the enumeration in the switch expression has already
//            implicitly specified the enum type of the case constants. There is no need to qualify the
//            constants in the case statements with their enum type name. In fact, attempting to do so
//            will cause a compilation error.
            case Jonathan:
            case Cortland:
                System.out.println("ap is either Jonathan or Cortland");
                break;

            default:
                System.out.println("ap is neither Jonathan or Cortland");
        }

        /*
        The values( ) method returns an array that contains a list of the enumeration constants. The
        valueOf( ) method returns the enumeration constant whose value corresponds to the string passed in str.
         */

        if(ap == Apple.Jonathan){
            //name of the constant (Jonathan) is displayed
            System.out.println("ap is " + ap + "\n");
        }

        // The values( ) method returns an array that contains a list of the enumeration constants.
        Apple allapples[] = Apple.values();

        for(Apple a : allapples)
            System.out.println(a);

        // use valueOf()
        ap = Apple.valueOf("Winesap");
        System.out.println("\nap contains " + ap);

        System.out.println("\nAll apple prices:");
        for(Apple a : Apple.values())
            System.out.println(a + " costs " + a.getPrice() + " cents.");

        /*
        You can obtain a value that indicates an enumeration constant’s position in the list of
        constants. This is called its ordinal value, and it is retrieved by calling the ordinal( ) method
         */
        System.out.println("Ordinal value of " + ap + " is " + ap.ordinal());

        comparison();
    }

    private static void comparison(){

        Apple ap = Apple.RedDel, ap2 = Apple.GoldenDel, ap3 = Apple.RedDel;

        //If the invoking constant has an ordinal value less than e’s, then compareTo( )
        //returns a negative value. If the two ordinal values are the same, then zero is returned. If the
        //invoking constant has an ordinal value greater than e’s, then a positive value is returned.
        if(ap.compareTo(ap2) < 0)
            System.out.println(ap + " comes before " + ap2);
        if(ap.compareTo(ap2) > 0)
            System.out.println(ap2 + " comes before " + ap);
        if(ap.compareTo(ap3) == 0)
            System.out.println(ap + " equals " + ap3);

/*      You can compare for equality an enumeration constant with any other object by using
        equals( ), which overrides the equals( ) method defined by Object. Although equals( ) can
        compare an enumeration constant to any other object, those two objects will be equal only
        if they both refer to the same constant, within the same enumeration. Simply having
        ordinal values in common will not cause equals( ) to return true if the two constants are
        from different enumerations.

        Remember, you can compare two enumeration references for equality by using ==*/

        if(ap.equals(ap2))
            System.out.println("Error!");
        if(ap.equals(ap3))
            System.out.println(ap + " equals " + ap3);
        if(ap == ap3)
            System.out.println(ap + " == " + ap3);
    }
}
