package LanguageCore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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


    }

    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    private static class Employee{
        private int salary;
        private String name;
    }
}
