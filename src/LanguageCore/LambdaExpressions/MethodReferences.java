package LanguageCore.LambdaExpressions;

/*A method reference provides a way to refer to a method without executing it. It relates to lambda expressions because it, too,
requires a target type context that consists of a compatible functional interface. When evaluated, a method reference also
creates an instance of the functional interface.*/
class MethodReferences {

    static void demo(){
        System.out.println();
    }
}

class Helper<T extends String>{

    private T helperStr;

    static int getMultiple(int num, int multiplier){
        return multiplier * num;
    }

 /*   T getNOf(T toAdd){

    }*/
}
