package LanguageCore.LambdaExpressions;

/*A method reference provides a way to refer to a method without executing it. It relates to lambda expressions because it, too,
requires a target type context that consists of a compatible functional interface. When evaluated, a method reference also
creates an instance of the functional interface.*/
class MethodReferences {

    static void demo(){

        IntHelper intHelper = new IntHelper();

        IntMultiple staticIntMultiple = IntHelper::getStaticMultiple, intMultiple = intHelper::getMultiple;

    }


}

class IntHelper{

    private int number = 10;
    private static int staticNumber = 5;

    static int getStaticMultiple(int multiplier){
        return staticNumber * multiplier;
    }

    int getMultiple(int multiplier){
        return staticNumber * multiplier;
    }
}

interface IntMultiple{
    int getMultipleOfNumber(int n);
}
