package LanguageCore.LambdaExpressions;

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
         */

        Predicate<String> isNull = Objects::isNull;
        System.out.println(isNull.test(null));

        Predicate<String> isNullOrEmpty = isNull.or(StringUtils::isEmpty);

        System.out.println("isJoeNullOrEmpty = " + Utils.applyPredicate(NAME, isNullOrEmpty));
        System.out.println("isnullNullOrEmpty = " + Utils.applyPredicate(null, isNullOrEmpty));

        Function<String, char[]> convertIntoCharArray = String::toCharArray;
        System.out.println("char array = " + Arrays.toString(Utils.applyFunction("lol", convertIntoCharArray)));

        BiFunction<Number, Number, Double> average = (num1, num2) -> ((num1.longValue() + num2.longValue()) / 2d);
        System.out.println("Average = " + average.apply(Integer.valueOf("21"), Integer.valueOf("20")));

        Supplier<Employee> employeeSupplier = Employee::new;

        Consumer<Employee> giveRaise = employee -> {
            double currentSalary = employee.getSalary();
            employee.setSalary(currentSalary + (currentSalary * 0.1));
        };
        List<Employee> salaryList = Collections.singletonList(employeeSupplier.get());
        salaryList.forEach(giveRaise);
        System.out.println("salaryList = " + salaryList);
    }

    private static class Utils {

        static <T> boolean applyPredicate(T toTest, Predicate<T> predicate){
            return predicate.test(toTest);
        }

        static <T,R> R applyFunction(T toApply, Function<T, R> function){
            return function.apply(toApply);
        }
    }

    @Setter
    @Getter
    @ToString
    private static class Employee {
        private double salary = 100.0;
    }
}
