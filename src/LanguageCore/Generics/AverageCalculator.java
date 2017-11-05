package LanguageCore.Generics;

/*
At its core, the term generics means parametrized types. Parametrized types are important because they enable you to
imp: create classes, interfaces and methods in which the type of data upon which they operate is specified as a parameter.

Java compiler does not actually create different versions of AverageCalculator, or of any other generic class. Although it’s helpful to think in
these terms, it is not what actually happens. Instead, the compiler removes all generic type information, substituting the necessary casts,
to make your code behave as if a specific version of AverageCalculator were created. Thus, there is really only one version of AverageCalculator
that actually exists in your program. imp: The process of removing generic type information is called erasure.

The ability to create type-safe code in which type-mismatch errors are caught at compile time is a key advantage of generics.
In essence, through generics, run-time errors are converted into compile-time errors. This is a major advantage.
*/
//As GenericInterface specifies <T extends Number> as the upper bound, AverageCalculator must specify <T extends Number/subclass of Number> as its upper bound
//imp: Once the type parameter T has been established, no need to specify <T extends Number> for GenericInterface since doing so will result in error
public class AverageCalculator<T extends Number> implements GenericInterface<T>{  // T is the type parameter, You can't use super in class declaration

    public static void genericsDemo(){

        Integer[] intArray = {9,1};
        Double[] doubleArray = {5.5, 4.5};

        //Integer is a type argument that is passed to AverageCalculator’s type parameter
        //imp: java compiler will infer T for AverageCalculator from AverageCalculator<Integer>
        AverageCalculator<Integer> integerAverageCalculator = new AverageCalculator<>(intArray);
        System.out.println("Average of intArray = " + integerAverageCalculator.getAverage());
        System.out.println("Min in intArray is " + integerAverageCalculator.min());

        AverageCalculator<Double> doubleAverageCalculator = new AverageCalculator<>(doubleArray);
        System.out.println("Average of doubleArray = " + doubleAverageCalculator.getAverage());
        System.out.println("Max in doubleArray is " + doubleAverageCalculator.max());

        /*imp: Multiple bounds

        In addition to using a class type as a bound, you can also use an interface type. In fact, you can specify multiple interfaces
        as bounds. Furthermore, a bound can include both a class type and one or more interfaces. In this case, the class type must be
        specified first. When a bound includes an interface type, only type arguments that implement that interface are legal. When specifying
        a bound that has a class and an interface, or multiple interfaces, use the & operator to connect them. For example,

        class Gen<T extends MyClass & MyInterface> { // ...

        Here, T is bounded by a class called MyClass and an interface called MyInterface. Thus, any type argument passed to T must be a
        subclass of MyClass and implement MyInterface.*/

        System.out.println("integerAverageCalculator.hasSameAverage(doubleAverageCalculator) = " + integerAverageCalculator.hasSameAverage(doubleAverageCalculator));

        GenericMethod.genericMethodDemo();

        GenericSuperClass<Integer, String> genericSuperClass = new GenericSuperClass<>(55, "superclassFieldValue");
        System.out.println("genericSuperClass.getObj() = " + genericSuperClass.getObj());
        System.out.println("genericSuperClass.getObjV() = " +genericSuperClass.getObjV());

        if(genericSuperClass instanceof GenericSuperClass<?, ?>){
            System.out.println("genericSuperClass is an instance of GenericSuperClass<?>");
        }

        //imp: following can't be compiled since generic type info does not exist at run time
        //boolean illegalStatement = genericSuperClass instanceof GenericSuperClass<Integer, String>;

        //imp: You can cast one instance of a generic class into another only if the two are otherwise compatible and their
        //imp: type arguments are the same. For example, assuming the foregoing program, this cast is legal:
        GenericClassHierarchy<Integer> integerGenericClassHierarchy = (GenericClassHierarchy<Integer>) genericSuperClass;
        //However, this is not legal:
        //GenericClassHierarchy<String> stringGenericClassHierarchy = (GenericClassHierarchy<String>) genericSuperClass;

    }

    private T[] numbers;

    private AverageCalculator(T[] numbers){
        this.numbers = numbers;
    }

    private double getAverage() {
        double sum = 0;

        for(int i = 0; i < numbers.length; i++){
            sum += numbers[i].doubleValue();
        }

        return sum / numbers.length;
    }

    private T[] getNumbers() {
        return numbers;
    }

    private void setNumbers(T[] numbers) {
        this.numbers = numbers;
    }

    //imp: The wildcard argument is specified by the ?, and it represents an unknown type.
    //Here, AverageCalculator<?> matches any AverageCalculator object, allowing any two AverageCalculator objects to have their averages compared
    //NOTE: Type of AverageCalculator created will still be governed by extends clause in AverageCalculator deceleration. Thus, ? essentially will
    //      always be of type Number or one of its subclasses
    //imp: Only classes that are superclasses of subclass are acceptable arguments. This is an inclusive clause: <? super subclass>
    private boolean hasSameAverage(AverageCalculator<?> other){
        return this.getAverage() == other.getAverage();
    }

    @Override
    public T max() {
        T maxNumber = null;

        for(Number n : numbers){

            if(n instanceof Double){
                if(maxNumber == null) maxNumber = (T) Double.valueOf(Double.MIN_VALUE);
                if(n.doubleValue() > maxNumber.doubleValue())
                    maxNumber = (T) n;
            }else if(n instanceof Integer){
                if(maxNumber == null) maxNumber = (T) Integer.valueOf(Integer.MIN_VALUE);
                if(n.intValue() > maxNumber.intValue())
                    maxNumber = (T) n;
            }else{
                throw new UnsupportedOperationException("max() only supports Double and Integer");
            }
        }

        return maxNumber;
    }

    @Override
    public T min() {
        T minNumber = null;

        for(Number n : numbers){

            if(n instanceof Double){
                if(minNumber == null) minNumber = (T) Double.valueOf(Double.MAX_VALUE);
                if(n.doubleValue() < minNumber.doubleValue())
                    minNumber = (T) n;
            }else if(n instanceof Integer){
                if(minNumber == null) minNumber = (T) Integer.valueOf(Integer.MAX_VALUE);
                if(n.intValue() < minNumber.intValue())
                    minNumber = (T) n;
            }else{
                throw new UnsupportedOperationException("max() only supports Double and Integer");
            }
        }

        return minNumber;
    }
}