package com.ker.java.lang.LanguageCore;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Characteristics of Streams:

• Not data structures — Streams have no storage. They carry values from a source through a pipeline of operations.
• They also never modify the underlying data structure (e.g., the List or array that the Stream wraps)
• Designed for lambdas. Most Stream methods take lambdas as arguments
• Do not support indexed access — You can ask for the first element, but not the second or third or last element. But, see next bullet.
• Can easily be output as arrays or Lists — Simple syntax to build an array or List from a Stream

Streams support lazy evaluation and parallel processing:

• Lazy - Most Stream operations are postponed until it is known how much data is eventually needed. E.g., if you do a 10-second-per-item operation on a 100 element list, then select the first
        entry, it takes 10 seconds, not 1000 seconds.
• Parallelizable - If you designate a Stream as parallel, then operations on it will automatically be done in parallel, without having to write explicit fork/join code.
    • Big idea
        Streams defer doing most operations until you actually need the results
    • Result
        Operations that appear to traverse Stream multiple times actually traverse it only once. Due to "short-circuit" methods, operations that appear to traverse entire stream can stop much earlier.
    • Examples
      stream. map(some0p).filter(someTest).findFirst().get()
       Does the map and filter operations one element at a time. Continues only until first match on the filter test.
      stream • map (...) • filter (...) • filter (...) • allMatch (someTest)
       Does the one map, two filter, and one allMatch test one element at time. The first time it gets false for the allMatch test, it stops.

imp: stream method categories

• Intermediate methods
  —  These are methods that produce other Streams. These methods don't get processed until there is some terminal method called.
    — Examples: map (and related mapToInt, flatMap, etc.), filter, distinct, sorted, peek, limit, skip, parallel, sequential, unordered
• Terminal methods
  —  After one of these methods is invoked, Stream is considered consumed and no more operations can be performed on it.
      • These methods can do a side-effect (forEach) or produce a value (findFirst)
    — Examples: forEach, forEachOrdered, toArray, reduce, collect, min, max, count, anyMatch, allMatch, noneMatch, findFirst, findAny, iterator

• Short-circuit methods
  —  These methods cause the earlier intermediate methods to be processed only until the short-circuit method can be evaluated.
      • Short-circuit methods can be intermediate (limit, skip) or terminal (findFirst, allMatch)
    — Examples: anyMatch, allMatch, noneMatch, findFirst, findAny, limit, skip

imp: Ways to make a Stream:
• From individual values
  - Stream.of(vall, val2, ...)
• From array
  - Stream.of(someArray), Arrays.stream(someArray)
  - IntStream.of(array)
• From List (and other collections)
  - someList.stream(), someOtherCollection.stream0
• From a "function"
  - Stream.generate, Stream.iterate
• From a file
  - Files.lines(somePath)
• From a StreamBuilder
  - someBuilder.build()
• From String
  - String.chars, Stream.of(someString.split(...))
• From another Stream
    distinct, filter, limit, map, sorted, skip

imp: turning streams into data structures:
• List (most common) — someStream.collect(Collectors.toList())
• Array (less common) — someStream.toArray(EntryType[]::new) • E.g., employeeStream.toArray(Employee[]::new)  NOTE: java will make an empty array and then the array is filled with elements
Note — You normally do this only at the end, after you have done all the cool Stream operations

imp: Imp Stream methods   // Onenote: Stream operations

• findFirst()   - lazy evaluation
  employees.map(...).findFirst()
  • Big idea
  — Returns an Optional for the first entry in the Stream. Since Streams are often results of filtering, there might not be a first entry, so the Optional could be empty.
    • There is also a similar findAny method, which might be faster for parallel Streams.
  — findFirst is faster than it looks when paired with map or filter. More details in section on lazy evaluation, but idea is that map or filter know to only find a single match and then stop.

• Reduce - reduction operations that take a Stream<T>, and combine or compare the entries to produce a single value of type T

imp: mapping methods
• mapToInt — Applies a function that produces an Integer, but the resultant Stream is an IntStream instead of a Stream<Integer>. Convenient because IntStream has zero-argument methods like sum, min, and max.
• mapToDouble - Similar to mapToInt, but produces DoubleStream.
• flatMap — Each function application produces a Stream, then the Stream elements are combined into a single Stream. For example, if company is a list of departments, this produces a list of all combined employees.
             company.flatMap(dept -> dept.employeeList().stream())
*/
@Log4j2
public class Streams {

    public static void demo(){
        Integer[] ints = new Integer[]{0,1,2};

        log.debug("ints forEach - ");
        // forEach is a terminal operation, which means that it consumes the stream and does not return a stream
        Arrays.stream(ints).forEach(System.out::println);   // forEach(Consumer)

        log.debug("ints peek, map, forEach - ");
        // peak is not an intermediary operation but not as efficient as forEach
        Integer[] integers = Arrays.stream(ints).peek(System.out::println).map(n -> n * 2).peek(System.out::println).toArray(Integer[]::new); //Downside: can't use break, continue and can't modify local vars
        log.debug("ints - {}, integers - {}", Arrays.toString(ints), integers);

        log.debug("parallel ints peek, map, forEach - ");
        // peak is not an intermediary operation but not as efficient as forEach
        Integer[] integersParallel = Arrays.stream(ints).parallel().peek(System.out::println).map(n -> n * 2).peek(System.out::println).toArray(Integer[]::new); //Downside: can't use break, continue and can't modify local vars
        log.debug("ints - {}, integersParallel - {}", Arrays.toString(ints), integersParallel);

        List<Employee> employeeList = Arrays.asList(new Employee(500, "KP"), new Employee(800, "Jake"), new Employee(6000, "KP"), new Employee(500, "KA"));
        Predicate<Employee> isKP = e -> e.getName().equals("KP");
        Predicate<Employee> isKPAndWellPaid = isKP.and(e -> e.getSalary() >= 5000);
        Optional<Employee> first = employeeList.stream().filter(isKPAndWellPaid).findFirst();
        log.debug("first = {}", first.orElse(null));

        List<Integer> salaries = Arrays.asList(100, 200, 300);
        List<Employee> employees = salaries.parallelStream().map(salary -> new Employee(salary, null)).collect(Collectors.toList());
        log.debug("employees - {}, total salary = {}", employees, employees.stream().mapToInt(Employee::getSalary).sum());

        List<List<String>> listList = new ArrayList<>();
        listList.add(Arrays.asList("test2", "test1"));
        listList.add(Arrays.asList("test3", "test2"));
        String[] strings = listList.parallelStream().flatMap(Collection::parallelStream).map(str -> "*" + str + "*").toArray(String[]::new);
        log.info("strings after flatmap = {}, here is listList - {}", Arrays.toString(strings), listList);

        log.info("sorted strings: {}", Arrays.stream(strings).distinct().sorted().collect(Collectors.toList()));
        log.info("Employees making most to least: {}", employeeList.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed().thenComparing(Employee::getName)).collect(Collectors.toList()));

        // Reduce - reduction operations that take a Stream<T>, and combine or compare the entries to produce a single value of type T
        // • Repeated combining — You start with a seed (identity) value, combine this value with the first entry of the Stream, combine the result with the second entry of the Stream, and so forth
        // • reduce is particularly useful when combined with map or filter
        // • Works in parallel if operator is associative and has no side effects
        Optional<Employee> richest = employeeList.stream().reduce((e1, e2) -> e1.getSalary() - e2.getSalary() >= 0 ? e1 : e2);
        log.debug("Richest employee = {}", richest.orElse(null));

        OptionalInt sumInt = Arrays.stream(ints).mapToInt(num -> num).reduce(Math::addExact);  // Class::StaticMethod    x -> Class.StaticMethod(x)
        log.debug("sumInt = {}", sumInt.orElse(Integer.MIN_VALUE));

        final String[] words = {"My", "name", "is", "Tom", "."};
        Optional<String> sentence = Arrays.stream(words).reduce(String::concat);    // Class::instanceMethod      x -> x.instanceMethod()
        log.debug("sentence = " + (sentence.orElse("null")));

        // Different ways of collecting
        Arrays.stream(words).collect(Collectors.toList());
        Arrays.stream(words).collect(Collectors.toSet());
        Arrays.stream(words).collect(Collectors.joining(" "));      // note that this does not add " " at the beginning or at the end
        Arrays.stream(words).collect(Collectors.toCollection(ArrayList::new));

        Map<Boolean, List<Employee>> map = employeeList.stream().collect(Collectors.partitioningBy(e -> e.getSalary() >= 5000));
        log.debug("boolean employee map - " + map.toString());

        Map<String, List<Employee>> employeeNameMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getName));
        log.debug("name employee map - " + employeeNameMap.toString());

        // parallel streams,   Onenote: Streams - Sequential vs Parallel streams
         int[] randomIntsArray =
        IntStream.generate(() -> new Random().nextInt(10000)).limit(10000000).toArray();
        LocalTime startTime = LocalTime.now();
        int sum = IntStream.of(randomIntsArray).parallel().map(num -> num * 100).sum();
        log.debug("parallel addition time = {}, sum = {}", Duration.between(startTime, LocalTime.now()).toMillis(), sum);
        startTime = LocalTime.now();
        sum = IntStream.of(randomIntsArray).map(num -> num * 100).sum();
        log.debug("Sequential addition time = {}, sum = {}", Duration.between(startTime, LocalTime.now()).toMillis(), sum);

        /* imp: unbounded stream [Stream.generate(valueGenerator) or Stream.iterate(initialValue, valueTransformer)]
        • Stream.generate(valueGenerator) Stream.generate lets you specify a Supplier. This Supplier is invoked each time the system needs a Stream element.
            • Powerful when Supplier maintains state, but then won't work in parallel
        • Stream.iterate(initialValue, valueTransformer) — Stream.iterate lets you specify a seed and a UnaryOperator f. The seed becomes the first element of the Stream,
        f(seed) becomes the second element, f(second) becomes third element, etc.

        imp: Why use unbounded streams?

        The values are not calculated until they are needed, so you don't have to fill in the data in advance when you don't know how many entries you will need
        • The point is not really that this is an "infinite" Stream, but that it is an unbounded "on the fly" Stream — one with no fixed size, where the values are calculated as you need them
        • Caution — To avoid unterminated processing, you must eventually use a size-limiting operation like limit or findFirst (but not skip alone)
        • E.g., calling "sort" or "count" directly on an infinite stream will crash your program

        unbounded streams not usually suited for parallel streams
        */

        Stream<Employee> unboundedEmployeeStream = Stream.generate(Employee::new).limit(20);
        // Stream<Employee> unboundedEmployeeStream = Stream.generate(Employee::new);
        // Employee[] employees1 = unboundedEmployeeStream.toArray(Employee[]::new);    // DO NOT DO THIS, will run out of memory

        Employee[] employees1 = unboundedEmployeeStream.toArray(Employee[]::new);
        log.debug("employees1 size = " + employees1.length);

        Stream<Employee> generate = Stream.generate(new EmployeeSalarySupplier()).limit(20);
        log.info("Employee salaries - ");
        generate.collect(Collectors.toList()).forEach(System.out::println);

        Stream<Integer> integerStream = Stream.iterate(0, n -> n + 1).limit(20);
        log.info("int stream with seed: ");
        integerStream.forEach(System.out::print);
    }

    @NoArgsConstructor
    public static class EmployeeSalarySupplier implements Supplier<Employee> {

        private int salary;

        @Override
        public Employee get() {
            return new Employee(salary++, "sameName");
        }
    }

    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Employee{
        private int salary;
        private String name;
    }
}
