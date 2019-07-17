package com.ker.java.lang.LanguageCore.LambdaExpressions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.*;

class Functions {

    private static final String NAME;

    static {
        NAME = "JOE";
    }

    static void demo(){
        /* java.util.function: Provides Functional interfaces - i.e. target types for lambda expressions and method references. Examples:
        — Predicate<T> T in, boolean out
        — Function<T,R> T in, R out
        — Consumers — T in, nothing (void) out
        — Supplier<T> — Nothing in, T out
        — BinaryOperator<T> Two T's in, T out

        imp: Functions can have the following:
        1. higher order function which is a default method which returns a lambda expression. Example: or, and in Predicate ; andThen in Function
        2. static methods in functional interface which return lambda expressions satisfying the abstract method of that interface. Example: identity in Function, equals in Predicate
         */

        Predicate<String> isNull = Objects::isNull;
        System.out.println(isNull.test(null));

        Predicate<String> isNullOrEmpty = isNull.or(StringUtils::isEmpty);    //imp: or is a higher order function in Predicate

        System.out.println("isJoeNullOrEmpty = " + Utils.applyPredicate(NAME, isNullOrEmpty));
        System.out.println("isnullNullOrEmpty = " + Utils.applyPredicate(null, isNullOrEmpty));

        Predicate<String> isEqual = Predicate.isEqual(NAME);
        System.out.println("isEqual.test(NAME + 'e') - " + isEqual.test(NAME + 'e'));

        Function<String, char[]> convertIntoCharArray = String::toCharArray;
        char[] charArray = Utils.applyFunction("lol", convertIntoCharArray);

        Function<char[], char[]> addzToCharArray = chars -> {
            char[] newChars = Arrays.copyOf(chars, chars.length + 1);
            newChars[newChars.length - 1] = 'z';
            return newChars;
        };

        Function<char[], char[]> add4ToCharArray = chars -> {
            char[] newChars = Arrays.copyOf(chars, chars.length + 1);
            newChars[newChars.length - 1] = '4';
            return newChars;
        };

        System.out.println("char array = " + Arrays.toString(Utils.applyFunctions(charArray, addzToCharArray, add4ToCharArray)));

        BiFunction<Number, Number, Double> average = (num1, num2) -> ((num1.longValue() + num2.longValue()) / 2d);
        System.out.println("Average = " + average.apply(Integer.valueOf("21"), Integer.valueOf("20")));

        Supplier<Employee> employeeSupplier = Employee::new;

        Consumer<Employee> giveRaise = employee -> {
            double currentSalary = employee.getSalary();
            employee.setSalary(currentSalary + (currentSalary * 0.1));
        };

        Consumer<Employee> giveBonus = employee -> {
            employee.setSalary(employee.getSalary() + 5000);
        };

        List<Employee> salaryList = Collections.singletonList(employeeSupplier.get());
        salaryList.forEach(giveRaise.andThen(giveBonus).andThen(System.out::println));
    }

    private static class Utils {

        static <T> boolean applyPredicate(T toTest, Predicate<T> predicate){
            return predicate.test(toTest);
        }

        static <T,R> R applyFunction(T toApply, Function<T, R> function){
            return function.apply(toApply);
        }

        @SafeVarargs
        static <T> T applyFunctions(T toApply, Function<T, T> ... functions){
            Function<T,T> result = Function.identity();      // Function.identity() forms implementation for Function.apply(T t) such that t -> (t)
            // above same as below
            Function<T,T> result1 = t -> t;

            for(Function<T,T> func : functions){
                result = result.andThen(func);
            }
            return result.apply(toApply);
        }
    }

    @Setter
    @Getter
    @ToString
    private static class Employee {
        private double salary = 100.0;
    }
}
