package LanguageCore.LambdaExpressions;

import static java.lang.Math.random;

public class LambdaExpression {

    static int staticInt = 44;
    int integer = 55;

    private double instanceDouble = 4d;

    /*Lambda operator or the arrow operator, is −> divides a lambda expression into two parts. The left side specifies any
    parameters required by the lambda expression. (If no parameters are needed, an empty parameter list is used.) On the right
    side is the lambda body, which specifies the actions of the lambda expression. The −> can be verbalized as “becomes” or “goes to.”*/
    public void demo(){

        /*Although it is possible to explicitly specify the type of a parameter, such as n in this case, often you won’t need to do so because
        in many cases its type can be inferred.
        imp: A lambda expression is not executed on its own. Rather, it forms the implementation of the abstract method defined by the functional interface
        that specifies its target type. As a result, a lambda expression can be specified only in a context in which a target type is
        defined. One of these contexts is created when a lambda expression is assigned to a functional interface reference. */

        //Lambda body consisting of single expression
        RandomNumberGenerator randomNum = () -> random();

        /*An instance of a class is automatically created that implements the functional interface, with the lambda expression defining the behavior of the abstract
        method declared by the functional interface. When that method is called through the target, the lambda expression is executed. Thus, a lambda expression gives
        us a way to transform a code segment into an object*/
        System.out.println("A random number is " + randomNum.getRandomNumber());
        System.out.println("Another random number is " + randomNum.getRandomNumber());

        double localDouble = 3d;

        //Lambda body consisting of a block of code
        RandomNumberGenerator randomNumberGenerator2 = () -> {
            double randomNumber = random();
            System.out.println("this refers to the class that the lambda resides in - " + this.integer);
            // instance variables can be modified and instance name hiding allowed
            double instanceDouble = 34;
            this.instanceDouble = 43;

            // local variable cannot be modified and local variable name hiding not allowed
            //double localDouble;
            //localDouble = 44;

            return randomNumber * 100;
        };      // Note ; after }

        randomNumberGenerator2.getRandomNumber();

        // using only 1 generic functional interface for both String and int
        Add<String> stringAdd = (t1, t2) -> t1 + " "  + t2;
        Add<Integer> intAdd = (t1, t2) -> t1 + t2;

        System.out.println(add(5, 6, intAdd));

        double aRandomNum = 0.5;

        /*imp: It is important to emphasize that a lambda expression can use and modify an instance variable from its invoking
        class. It just can’t use a local variable of its enclosing scope unless that variable is effectively final.
        when a lambda expression uses a local variable from its enclosing scope, a special situation is created that is referred
        to as a variable capture. In this case, a lambda expression may only use local variables that are effectively final.
        An effectively final variable is one whose value does not change after it is first assigned. There is no need to
        explicitly declare such a variable as final, although doing so would not be an error.*/
        RandomNumberGenerator generateAndStoreRandomNum = () -> {

            thisCanBeModifiedByLambda = random() * aRandomNum;
            //aRandomNum = thisCanBeModifiedByLambda;
            return thisCanBeModifiedByLambda;
        };

        MethodReferences.demo();
        Functions.demo();
    }

    // passing executable code as an argument to a method
    static <T> T add(T a1, T a2, Add<T> addMethod){
        T sum = null;
        try {
            sum = addMethod.add(a1, a2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    private static double thisCanBeModifiedByLambda;
}

/*
a functional interface has exactly one abstract method. Since default methods have an implementation, they are not abstract. If an interface declares an abstract method overriding one of the
public methods of java.lang.Object, that also does not count toward the interface's abstract method count since any implementation of the interface will have an implementation from
java.lang.Object or elsewhere.
*/
@FunctionalInterface
interface RandomNumberGenerator{
    double getRandomNumber();
}

/*A lambda expression, itself, cannot specify type parameters. Thus, a lambda expression cannot be generic.
However, the functional interface associated with a lambda expression can be generic. In this case, the target type of the lambda expression
is determined, in part, by the type argument or arguments specified when a functional interface reference is declared.*/
@FunctionalInterface
interface Add<T> {
    //imp: A lambda expression can throw an exception. However, it if throws a checked exception, then that exception must be compatible
    //imp: with the exception(s) listed in the throws clause of the abstract method in the functional interface.
    T add(T t1, T t2) throws Exception;
}