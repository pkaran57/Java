package LanguageCore.Generics;

import LanguageCore.Generics.Helpers.Pair;

// It is possible to create a generic method that is enclosed within a non-generic class.
class GenericMethod<T> {

    T[] elements;

    //imp: generic constructor
    <X extends String> GenericMethod(X x){

    }

    public static void genericMethodDemo(){

        Pair<Integer, String> p1 = new Pair<>(1, "apple");
        Pair<Integer, String> p2 = new Pair<>(2, "pear");
        boolean same = GenericMethod.<Integer, String>compare(p1, p2);

        //The type has been explicitly provided, as shown in bold. Generally, this can be left out and the compiler will infer the type that
        // is needed:

        //imp: TYPE INFERENCE allows you to invoke a generic method as an ordinary method, without specifying a type between angle brackets
        //Generally, a Java compiler can infer the type parameters of a generic method call. Consequently, in most cases, you do not have to specify them.
        same = GenericMethod.compare(p1, p2);
        // Thus, this is redundant: same = GenericMethod.<Integer, String>compare(p1, p2);
        GenericMethod.compare(p1, new Pair<>(2, "pear"));   //Type parameters for Pair will be inferred from passed in argument types

        // generic method in a generic class
        Object o = new GenericMethod<Integer>("");
    }

    // The syntax for a generic method includes a list of type parameters, inside angle brackets, which appears before the method's return type
    //NOTE: removing <K, V> after static will cause an error
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }
}
