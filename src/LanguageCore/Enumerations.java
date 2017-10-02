package LanguageCore;

//enumeration is a list of named constants
//in Java, an enumeration defines a class type
/*
all enumerations automatically inherit one: java.lang.Enum.
This class defines several methods that are available for use by all enumerations

Here are two restrictions that apply to enumerations. First, an enumeration can’t
inherit another class. Second, an enum cannot be a superclass. This means that an enum
can’t be extended. Otherwise, enum acts much like any other class type. The key is to
remember that each of the enumeration constants is an object of the class in which it is defined.*/

public class Enumerations {

    // An enumeration of apple varieties.
    // Jonathan, GoldenDel, RedDel, Winesap, Cortland are called enum constants
    //Each is implicitly declared as a public, static final member of Apple
    //each enumeration constant is an object of its enumeration type.
    private enum Apple {

        Jonathan(10), GoldenDel(9), RedDel(12), Winesap(15), Cortland;

        private int price; // price of each apple

        Apple() {price = -1;}

        // Constructor
        Apple(int p) { price = p; }
        int getPrice() { return price; }
    }

    public static void enumDemo(){

        Apple ap = Apple.Jonathan;
        Apple ap1 = Apple.Cortland;

        switch(ap){
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
    }
}
