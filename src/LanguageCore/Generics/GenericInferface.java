package LanguageCore.Generics;

/*
This is wrong: class MyClass extends GenericInterface<T> { ... }
However, this is fine: class MyClass extends GenericInterface<Integer> { ... }   //as long as a specific type is passed as type argument, it should be fine
*/
interface GenericInterface<T extends Number> {
    T max();
    T min();
}
