//.java name extension is required for each and every java source code file
// System is a class predefined by java and automatically included. Ex: No need to import anything to use System.out.println()
// java.lang package is imported implicitly

import LanguageCore.ExceptionHandling;
import LanguageCore.Streams;
import LanguageCore.oopdemo.CEO;
import LanguageCore.oopdemo.Employee;
import LanguageCore.oopdemo.abstractClass.Shape;
import LanguageCore.oopdemo.abstractClass.Square;
import LanguageCore.oopdemo.interfaces.Atorvastatin;
import LanguageCore.oopdemo.interfaces.GenericDrug;

/*imp: Static Import
 By following import with the keyword static, an import statement can be used to import the static members of a class or interface.
 When using static import, it is possible to refer to static members directly by their names, without having to qualify them with the
 name of their class. This simplifies and shortens the syntax required to use a static member.*/

//All java applications begin by calling main method
//The method main must be declared  public, static, and void. It must accept a single argument that is an array of strings
public class Main {

    public static void main(String[] args) {

        //out.println("Do not need to call System since all static members of System class are imported by static import");

        //Onenote : Read up to (including) Java language characteristics
        //variableAndLiterals();
        //arrays();
        //controlStatements();
        //Onenote: OOP [Access modifiers and (Packages and CLASSPATH)]
        //oopDemo();
        //exception_handling();
        //MultiThreading.main();
        //Enumerations.enumDemo();
        //TypeWrappersAndAutoBoxing();
        //Annotations.annotationsDemo();
        //Asserts();
        //AverageCalculator.genericsDemo();
        //Onenote: Generics
        //LambdaExpression.demo();
        Streams.demo();
    }

    //variables which are member of a class are given default values if not initialized
    private static int jjjj;       //default value is 0
    private static Object o;    //default value is null

    private static void variableAndLiterals(){

        //variables in a method must be initialized before use !
        int mustBeInitializedBeforeUse;

        int i = 100;
        //i is converted from an int into string before concatenation !
        System.out.println("i is " + i + " and j is " + jjjj + " and object o is " + o);

        //Onenote : Read java basics

        //Topic : Literals
        i = 01;     // Octal values are denoted in Java by a leading zero
        i = 0x9f;   // Hex values are denoted in Java by a leading 0x or 0X
        i = 0b1011; // Binary values are denoted in Java by a leading 0b or 0B

        /*
        An integer literal can always be assigned to a long variable. However, to specify a long
        literal, you will need to explicitly tell the compiler that the literal value is of type long. You
        do this by appending an upper- or lowercase L to the literal. For example, 0x7ffffffffffffffL
         */
        long ii = 0x7ffff__ffff__ffff__ffL;     // okay to have underscores in an integer literal

        float f = 2.0f;     // float literals need a f or F at the end
        f = 2E-2f;          //scientific notation, meaning : 2 * 10 ^ -2
        double d = 2.0;     // 2.0 is considered as a double literal

        //chars can be assigned unicode values in decimal, hex as well as octal
        char c = 9985;
        System.out.println(c);
        c = 0x2701;
        System.out.println(c);

        System.out.println("this is a unicode character : \u2701");     // hex unicode character escape sequence

        //strings are immutable objects in java and not an array of characters like in some other languages

        //declaring and initializing multiple variables of same type in one line
        int a = 3, ee, b = 2;       // ee is not initialized here

        //topic : automatic and explicit casting

        //widening occurs implicitly
        byte bb = 1;
        short ss = bb;
        int iii = ss;
        long ll = iii;

        //conversion of int types to float or double occur implicitly
        float ff = ll;
        double dd = bb;


        //need explicit conversion from float or double to integer type, truncation occurs !
        iii = (int) ff;
        ss = (short) dd;

        iii = 9999;      // 9999 will be out of range of byte
        bb = (byte) iii; //value will be reduced modulo the target type’s range, i.e. remainder of 9999 / range of byte (2^8 = 256)
        System.out.println("bb is " + bb);

        //imp: Java automatically promotes each byte, short, or char operand to int when evaluating an expression
        bb = 50;
        bb = (byte) (bb * bb);       //since both bb's are promoted to ints and the expression returns an int, we need to perform an explicit cast

        /*Topic : type promotions which apply to expressions

        Java defines several type promotion rules that apply to expressions. They are as follows: First,
        all byte, short, and char values are promoted to int, as just described. Then, if one operand
        is a long, the whole expression is promoted to long. If one operand is a float, the entire
        expression is promoted to float. If any of the operands are double, the result is double.
         */
    }

    static boolean[] staticArray; // this array is initialized to null

    private static void arrays(){
        //arrays are implemented as objects in java

        //2 ways to declare an array variable:
        int a[];    //int array variable
        int[] aa;   //another way of writing an int array variable

        //The elements in the array allocated by new will automatically be initialized to zero (for numeric types), false
        //(for boolean), or null (for reference types, which are described in a later chapter).
        aa = new int[5];

        for(int i = 0; i < 5; i++){

            System.out.println("aa[" + i + "] = " + aa[i]);
        }

        int aaa[] = {1,2,3,4,5,6,7,8,9};    //another way of initializing array other than using new keyword
        //a = {1,2,3,4,5,6,7,8,9};    //since a[] is already declared above, we can't initialize it using above syntax

        int[][] twoDArray = { {1,2,3},
                            {4,5,6},
                            {7,8,9}};

        System.out.println("twoDArray[2][1] = " + twoDArray[2][1]);     //this will print 8

        /*
        When you allocate memory for a multidimensional array, you need only specify the
        memory for the first (leftmost) dimension. You can allocate the remaining dimensions separately
         */
        int[][] twoD = new int[3][];
        twoD[0] = new int[1];
        twoD[1] = new int[2];
        twoD[2] = new int[3];

        int[][] twoDD = new int[3][7];
        for(int i = 0; i < twoDD.length;i++) System.out.println("twoDD[" + i + "].length = " + twoDD[i].length);

        //imp: creating multiple array variables on a single line
        char[] b,c,d;
        //above statement is same as writing :
        char x[], y[], z[];
    }

    private static void controlStatements(){

        int i = 3;

        //if, else if and else statements
        if(i == 1){
            System.out.println("i is 1");
        }
        else if(i == 2){
            System.out.println("i is 2");
        }
        else if(i == 3){
            System.out.println("i is 3");
        }
        else{
            System.out.println("i is none of the above");
        }

        /*
        for(initialization; condition; iteration) statement;

        the initialization portion of the loop sets a loop control variable to an initial value. The condition is a Boolean
        expression that tests the loop control variable. If the outcome of that test is true, the for loop continues to iterate.
        If it is false, the loop terminates. The iteration expression determines how the loop control variable is
        changed each time the loop iterates.
         */
        //imp: notice that defining i again below will result in an error since i is already defined above
        for(i = 3; i < 3; i++){
            System.out.println("From for : i is " + i);
        }

        //switch statements

        String s = "ballo";

        //imp: expression must be of type byte, short, int, char, string or an enumeration in : switch(expression) {};
        switch(s){
            case "hey":         // Duplicate case values are not allowed
                System.out.println("s is hey");
                break;

            case "ballo":
            case "hello":               // since there is no break statement in this case, execution falls through all other
                                        // cases irrespective of truth unless a break statement is discovered
                System.out.println("s is hello or ballo");

            case "allo":
                System.out.println("s is allo");
                break;

            default:
                System.out.println("s is neither");
        }

        //while statement, while condition in brackets is true, keep on executing the body
        //while(true){ }  //infinite loop

        //do while loop, do part gets executed at least once and then again and again while the condition in () is true
        do{
            System.out.println("From do while");
        }while(false);

        //for each loop
        int[] ii = {1,2,3,4};

        System.out.println("members of ii are :");
        for(int iii : ii) System.out.println(iii);

        //break statement
        // NOTE: Even if break is within the if statement, it will break out of the (for) loop [if statement is not a loop]
        for(i=0; i<100; i++) {
            if(i == 3) break; // terminate loop if i is 3, "break out of the loop"
            System.out.println("break i: " + i);
        }
        System.out.println("Loop complete.");

        //imp: break with label
        boolean t = true;
        first: {
            second: {
                third: {
                    System.out.println("Before the break.");
                    if(t) break second; // break out of second block
                    System.out.println("This won't execute");
                }
                System.out.println("This won't execute");
            }
            System.out.println("This is after second block.");
        }

        //continue statement
        for(i=0; i<4; i++) {
            if(i == 1) continue; // begin with next iteration if i == 1
            System.out.println("continue i: " + i);
        }

        //imp: continue with label
        outer: for ( i=0; i<10; i++) {     // continuing will not cause the loop to restart
            for(int j=0; j<10; j++) {
                if(j > i) {
                    System.out.println();
                    continue outer;             //continue at outer
                }
                System.out.print(" " + (i * j));
            }
        }
    }

    private static void oopDemo(){

        /*
        Take a look at these in order:
        Employee.java
        CEO.java
        Shape.java
        Square.java
         */

        //initializing a static inner class instance
        //imp: static inner classes behave as a top level class since it does not need an instance of enclosing class for it to be created
        Employee.StaticInnerClass innerStatic = new Employee.StaticInnerClass();    //create an object for the static nested class
        innerStatic.print();

        /*it is possible that new will not be able to allocate memory for an object because insufficient memory
        exists. If this happens, a run-time exception will occur. */
        Employee e1 = new Employee();

        // initializing a non-static inner class instance
        //imp: inner class needs an instance of enclosing class for inner class to be initialized
        Employee.InnerClass inner = e1.new InnerClass();
        Employee.InnerClass inner3 = e1.new InnerClass();  // possible to create as many instances from 1 super-class instance

        inner.print();
        e1.innerVar = "modified";
        System.out.println("\n*** AFTER MODIFICATION ***\n");
        inner.print();
        System.out.println("\n*** Calling print() on inner3 ***\n");
        inner3.print();

        // using new instance of Employee to initialize inner class
        Employee.InnerClass inner2 = new Employee("Inner").new InnerClass();

        e1.varLengthArgs(0,"as");

        //Onenote: OOP [Inner class shadowing]

        Employee employee = new CEO(150000, "Jake");
        //when a reference to a subclass object is assigned to a superclass reference variable, you will
        //have access only to those parts of the object defined by the superclass
        //employee.exclusiveTOCEO();    // this will not compile

        //this calls CEO's getObjectType() method
        employee.getObjectType();

        //Onenote: OOP [Abstract Classes vs Interface]
        //Onenote : OOP[Object class]

        Shape shape = new Square("I am a square !!!", 5);
        shape.printShapeDescription();

        /*objref instanceof type

        Here, objref is a reference to an instance of a class, and type is a class type. If objref is of
        the specified type or can be cast into the specified type, then the instanceof operator
        evaluates to true. Otherwise, its result is false.*/
        Employee employee1 = new Employee();

        if(employee1 instanceof CEO){
            CEO ceo = (CEO) employee1;
        } else{
            System.out.println("Can't cast Employee object to CEO");
        }

        //take a look at GenericDrug.java and Atorvastatin.java
        Atorvastatin drug1 = new Atorvastatin();
        drug1.printInfo();
        //imp: casting an object to interface type
        Object object = new Atorvastatin();
        GenericDrug drug2 = (GenericDrug) object;

        System.out.println("************");
        drug2.printInfo();      //imp: will call Atorvastatin's printInfo
        //Onenote : OOP [interface tips]
    }

    private static void exception_handling(){
        // take a look at all methods in Execption_handling.java !
        // keywords: try, catch, throw, throws, finally, try with resource
        ExceptionHandling.tryCatch();
        ExceptionHandling.multipleCatch();
        String[] s = {"S"};
        ExceptionHandling.nestedTry(s);
        ExceptionHandling.noHandler();
    }

    private static void TypeWrappersAndAutoBoxing(){

        /*
        As you know, Java uses primitive types (also called simple types), such as int or double, to
        hold the basic data types supported by the language. Primitive types, rather than objects,
        are used for these quantities for the sake of performance. Using objects for these values
        would add an unacceptable overhead to even the simplest of calculations.

        Despite the performance benefit offered by the primitive types, there are times when
        you will need an object representation. For example, you can’t pass a primitive type by
        reference to a method. Also, many of the standard data structures implemented by Java
        operate on objects, which means that you can’t use these data structures to store primitive
        types. To handle these (and other) situations,
        imp: Java provides type wrappers, which are classes that encapsulate a primitive type within an object.

        The type wrappers are Double, Float, Long, Integer, Short, Byte, Character, and Boolean [all class names]
         */

        Integer i1 = Integer.valueOf(2);
        //If str does not contain a valid numeric value, then a NumberFormatException is thrown.
        Integer i2 = Integer.valueOf("3");

        //All of the type wrappers override toString( ). It returns the human-readable form of the
        //value contained within the wrapper. All type wrappers have typeValue() methods like the intValue() below
        System.out.println("i1 is " + i1 + " and i2 is " + i2.intValue());

        //imp: The process of encapsulating a value within an object/type wrapper is called boxing. (auto)boxing below:
        Integer i3 = 6;
        //imp: The process of extracting a value from a type wrapper is called unboxing.(auto)unboxing below:
        int i4 = i3;

        /*
        Auto-unboxing also allows you to mix different types of numeric objects in an
        expression. Once the values are unboxed, the standard type promotions and conversions
        are applied. For example, the following program is perfectly valid:
         */
        double d = i3 / 1.0;

        i3++;  // auto-boxing magic
    }

    public static void Asserts(){

        /*At run time, if the condition is true, no other action takes place. However, if the condition is false, then an AssertionError is thrown.
        Assertions are often used during testing to verify that some expected condition is actually met. imp: They are not usually used for released code.
        One important point to understand about assertions is that you must not rely on them to perform any action actually required by the program.
        The reason is that normally, released code will be run with assertions disabled

        Two forms of assert:
        1. assert condition;       // If the condition is false, then the assertion fails and a default AssertionError object is thrown.
        2. assert condition: expr;
            In this version, expr is a value that is passed to the AssertionError constructor. This value is
            converted to its string format and displayed if an assertion fails. Typically, you will specify a
            string for expr, but any non-void expression is allowed as long as it defines a reasonable
            string conversion.
        */

        assert false: "false will cause the assert to throw AssertionError";

        /*Assertion Enabling and Disabling Options

        When executing code, you can disable all assertions by using the -da option. You can enable or disable a specific package (and all of its subpackages)
        by specifying its name followed by three periods after the -ea or -da option. For example, to enable assertions in a package called MyPack, use  -ea:MyPack...
        To disable assertions in MyPack, use  -da:MyPack...
        You can also specify a class with the -ea or -da option. For example, this enables AssertDemo individually: -ea:AssertDemo*/
    }
}