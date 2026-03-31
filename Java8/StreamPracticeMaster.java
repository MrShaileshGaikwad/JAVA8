package Java8;

import java.util.*;
import java.util.stream.*;



public class StreamPracticeMaster {

    public static void main(String[] args) {

        List<Employee1> employees = Arrays.asList(
                new Employee1("Shailesh", "IT", 12000),
                new Employee1("Lokesh", "HR", 15000),
                new Employee1("Anish", "IT", 2000),
                new Employee1("Kalpesh", "Finance", 8000),
                new Employee1("Mahesh", "IT", 112000),
                new Employee1("Ravi", "HR", 4000)
        );

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,2,3,4);
        List<String> names = Arrays.asList("Java", "Stream", "API", "Code", "Java");

        // ================= BASIC FILTER =================
        System.out.println("Filter > 5:");
        numbers.stream().filter(n -> n > 5).forEach(System.out::println);

        // ================= MAP =================
        System.out.println("\nSquare numbers:");
        numbers.stream().map(n -> n * n).forEach(System.out::println);

        // ================= DISTINCT =================
        System.out.println("\nDistinct:");
        numbers.stream().distinct().forEach(System.out::println);

        // ================= SORT =================
        System.out.println("\nSorted Desc:");
        numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        // ================= LIMIT & SKIP =================
        System.out.println("\nLimit 3:");
        numbers.stream().limit(3).forEach(System.out::println);

        System.out.println("\nSkip 3:");
        numbers.stream().skip(3).forEach(System.out::println);

        // ================= COUNT =================
        System.out.println("\nCount:");
        System.out.println(numbers.stream().count());

        // ================= MAX / MIN =================
        System.out.println("\nMax:");
        System.out.println(numbers.stream().max(Integer::compare).get());

        System.out.println("\nMin:");
        System.out.println(numbers.stream().min(Integer::compare).get());

        // ================= REDUCE =================
        System.out.println("\nSum:");
        System.out.println(numbers.stream().reduce(0, Integer::sum));

        // ================= FIND =================
        System.out.println("\nFind First:");
        numbers.stream().findFirst().ifPresent(System.out::println);

        // ================= MATCH =================
        System.out.println("\nAny Match > 8:");
        System.out.println(numbers.stream().anyMatch(n -> n > 8));

        System.out.println("\nAll Match > 0:");
        System.out.println(numbers.stream().allMatch(n -> n > 0));

        // ================= STRING OPERATIONS =================
        System.out.println("\nUppercase:");
        names.stream().map(String::toUpperCase).forEach(System.out::println);

        System.out.println("\nLength > 4:");
        names.stream().filter(s -> s.length() > 4).forEach(System.out::println);

        // ================= FLATMAP =================
        System.out.println("\nFlatMap:");
        List<List<Integer>> nested = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(3,4)
        );
        nested.stream().flatMap(List::stream).forEach(System.out::println);

        // ================= GROUPING =================
        System.out.println("\nGroup by Dept:");
        Map<String, List<Employee1>> group =
                employees.stream().collect(Collectors.groupingBy(Employee1::getDept));
        System.out.println(group);

        // ================= PARTITION =================
        System.out.println("\nPartition salary > 10000:");
        Map<Boolean, List<Employee1>> partition =
                employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 10000));
        System.out.println(partition);

        // ================= COUNT PER GROUP =================
        System.out.println("\nCount per Dept:");
        Map<String, Long> countDept =
                employees.stream().collect(Collectors.groupingBy(
                        Employee1::getDept,
                        Collectors.counting()
                ));
        System.out.println(countDept);

        // ================= MAX SALARY =================
        System.out.println("\nMax Salary Employee:");
        System.out.println(
                employees.stream()
                        .max(Comparator.comparing(Employee::getSalary))
                        .orElse(null)
        );

        // ================= SECOND HIGHEST =================
        System.out.println("\nSecond Highest:");
        System.out.println(
                numbers.stream()
                        .distinct()
                        .sorted(Comparator.reverseOrder())
                        .skip(1)
                        .findFirst()
                        .orElse(null)
        );

        // ================= MAP CONVERSION =================
        System.out.println("\nList -> Map:");
        Map<String, Integer> map =
                employees.stream().collect(Collectors.toMap(
                        Employee::getName,
                        Employee::getSalary
                ));
        System.out.println(map);

        // ================= MAP -> LIST =================
        System.out.println("\nMap -> List<Employee>:");
        Map<String, Integer> data = new HashMap<>();
        data.put("A", 100);
        data.put("B", 200);

        List<Employee1> empList =
                data.entrySet().stream()
                        .map(e -> new Employee1(e.getKey(), "NA", e.getValue()))
                        .toList();

        System.out.println(empList);

        // ================= NULL HANDLING =================
        System.out.println("\nNull safe:");
        List<String> listWithNull = Arrays.asList("A", null, "B");

        listWithNull.stream()
                .filter(Objects::nonNull)
                .forEach(System.out::println);

        // ================= JOIN =================
        System.out.println("\nJoin:");
        System.out.println(
                names.stream().collect(Collectors.joining(","))
        );

        // ================= FREQUENCY =================
        System.out.println("\nFrequency:");
        System.out.println(
                numbers.stream().collect(Collectors.groupingBy(
                        n -> n,
                        Collectors.counting()
                ))
        );

        // ================= ARRAY STREAM =================
        System.out.println("\nArray Stream:");
        int[] arr = {1,2,3,4};
        Arrays.stream(arr).forEach(System.out::println);

        // ================= OPTIONAL =================
        System.out.println("\nOptional Example:");
        Optional<Integer> opt =
                numbers.stream().filter(n -> n > 100).findFirst();

        System.out.println(opt.orElse(0));

        // ================= PEEK =================
        System.out.println("\nPeek Debug:");
        numbers.stream()
                .filter(n -> n > 5)
                .peek(n -> System.out.println("After filter: " + n))
                .map(n -> n * 2)
                .forEach(System.out::println);

        // ================= PARALLEL =================
        System.out.println("\nParallel Stream:");
        numbers.parallelStream().forEach(System.out::println);
    }
}
