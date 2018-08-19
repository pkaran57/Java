package LanguageCore.LambdaExpressions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static LanguageCore.LambdaExpressions.LambdaExpression.add;

/*A method reference provides a way to refer to a method without executing it. It relates to lambda expressions because it, too,
requires a target type context that consists of a compatible functional interface. When evaluated, a method reference also
creates an instance of the functional interface.*/
class MethodReferences {

    static void demo(){

        IntHelper intHelper = new IntHelper();

        // method references to a static method and an instance method
        IntMultiple staticIntMultiple = IntHelper::getStaticMultiple, intMultiple = intHelper::getMultiple;

        //imp: 4 possibilities:
        // Class::StaticMethod                          x -> Class::StaticMethod(x)
        // variableInstance::instanceMethod             () -> variableInstance.instanceMethod()
        // Class::instanceMethod                        x -> x.instanceMethod()
        // Class::new                                   () -> new Class ()

        System.out.println(add(10, 6, Math::addExact));
        System.out.println(add(10, 6,
            (t1, t2) -> {
                  int addition = t1 + t2 + 1;
                  System.out.println("Faulty addition");
                  return addition;
            }));

        //Onenote: Method References

        System.out.println("staticIntMultiple.getMultipleOfNumber(5) = " + staticIntMultiple.getMultipleOfNumber(5));
        System.out.println("intMultiple.getMultipleOfNumber(5) = " + intMultiple.getMultipleOfNumber(5));
        intHelper.setNumber(100);
        System.out.println("After intHelper.setNumber(100), intMultiple.getMultipleOfNumber(5) = " + intMultiple.getMultipleOfNumber(5));

        final String fdf = "fdf";
        ProcessStringInstance stringProcessor1 = fdf::toUpperCase;   // variableInstance::instanceMethod        () -> variableInstance.instanceMethod()
        System.out.println(stringProcessor1.upper());
        System.out.println("fdf = " + fdf);    // note, will not modify current string to upper case!

        ProcessStringClass stringProcessor = String::toUpperCase;   // Class::instanceMethod                     x -> x.instanceMethod()
        List<String> stringList = new ArrayList<>();
        stringList.add("test1");
        stringList.add("test2");
        for(String str : stringList) stringProcessor.upper(str);
        //Alternatively, following works too:
        stringList.forEach(String::toUpperCase);   //behaves as if: for (T t : this) {action.accept(t);     see javadoc for forEach

        Supplier<IntHelper> intHelperSupplier = IntHelper::new;     // Class::new         () -> new Class ()
        IntHelper intHelper1 = intHelperSupplier.get();

        //imp: following is the deceleration of abstract method in IsGreaterOrEqual<T> interface- boolean isGreaterOrEqual(T invokingObject, T other);
        IsGreaterOrEqual<IntHelper> isGreater = IntHelper::isGreaterOrEqual;
        System.out.println("isGreater.isGreaterOrEqual(new IntHelper(), new IntHelper()) = " + isGreater.isGreaterOrEqual(new IntHelper(), new IntHelper()));

        //imp: You can refer to the superclass version of a method by use of super, as shown here- super::name
        //The name of the method is specified by name. Another form is typeName.super::name where typeName refers to an enclosing class or super interface

        IntHelper[] intHelpers = {new IntHelper(3), new IntHelper(4)};

        CompareMultiple<IntHelper> compareMultiple = IntHelper::compareMultiple;
        //imp: Method References with Generics
        //It is important to point out, however, that explicitly specifying the type argument is not required in this situation (and many others)
        // because the type argument would have been automatically inferred.
        System.out.println("compareMultiple.compareMultiple(intHelpers): " + compareMultiple.<IntHelper>compareMultiple(intHelpers));

        ArrayList<IntHelper> intHelpersList = new ArrayList<>();
        intHelpersList.add(new IntHelper(5));
        intHelpersList.add(new IntHelper(55));
        IntHelper maxIntHelper = Collections.max(intHelpersList, IntHelper::compare);
        System.out.println("maxIntHelper.getNumber() = " + maxIntHelper.getNumber());
    }
}

class IntHelper{

    private int number = 10;
    private static int staticNumber = 5;

    public IntHelper(){ }

    /*imp: Initializer are executed before constructor bodies. (Which has implications if you have both Initializer and constructors,
    the constructor code executes second and overrides an initialised value)*/
    public IntHelper(int num){
        number = num;
    }

    static int getStaticMultiple(int multiplier){
        return staticNumber * multiplier;
    }

    int getMultiple(int multiplier){
        return number * multiplier;
    }

    void setNumber(int number) {
        this.number = number;
    }

    int getNumber() {
        return number;
    }

    boolean isGreaterOrEqual(IntHelper otherIntHelper){
        return number >= otherIntHelper.getNumber();
    }

    static <T extends IntHelper> boolean compareMultiple(T[] multiples){
        for(T object: multiples) {
            if(object.getNumber() != staticNumber) return false;
        }
        return true;
    }

    public static int compare(IntHelper intHelper1, IntHelper intHelper2){
        return intHelper1.getNumber() - intHelper2.getNumber();
    }
}

@FunctionalInterface
interface IntMultiple{
    int getMultipleOfNumber(int n);
}

@FunctionalInterface
interface ProcessStringClass {
    String upper(String str);
}

@FunctionalInterface
interface ProcessStringInstance{
    String upper();
}

@FunctionalInterface
interface IsGreaterOrEqual<T>{
    boolean isGreaterOrEqual(T invokingObject, T other);
}

@FunctionalInterface
interface CompareMultiple<T>{
    boolean compareMultiple(T[] multiples);
}

@FunctionalInterface
interface Initializer{
    IntHelper initializer(int n);
}