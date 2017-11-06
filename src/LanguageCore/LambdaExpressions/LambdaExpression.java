package LanguageCore.LambdaExpressions;

import static java.lang.Math.*;

public class LambdaExpression {

    /*Lambda operator or the arrow operator, is −> divides a lambda expression into two parts. The left side specifies any
    parameters required by the lambda expression. (If no parameters are needed, an empty parameter list is used.) On the right
    side is the lambda body, which specifies the actions of the lambda expression. The −> can be verbalized as “becomes” or “goes to.”*/
    public static void demo(){

        /*Although it is possible to explicitly specify the type of a parameter, such as n in this case, often you won’t need to do so because
        in many cases its type can be inferred.
        imp: A lambda expression is not executed on its own. Rather, it forms the implementation of the abstract method defined by the functional interface
        that specifies its target type. As a result, a lambda expression can be specified only in a context in which a target type is
        defined. One of these contexts is created when a lambda expression is assigned to a functional interface reference. Other target type
        contexts include variable initialization, return statements, and method arguments, to name a few.*/
        //Lambda body consisting of single expression
        RandomNumberGenerator randomNum = () -> random();

        System.out.println("A random number is " + randomNum.getRandomNumber());
        System.out.println("Another random number is " + randomNum.getRandomNumber());
    }
}

// a functional interface is an interface that specifies only one abstract method
interface RandomNumberGenerator{
    double getRandomNumber();
}