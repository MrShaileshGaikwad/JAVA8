package Java8;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Optional;
import java.util.Map.*;

/**
 * ============================================================
 *       JAVA 8 STREAMS — COMPLETE MASTER CLASS
 *       100+ Problems with Solutions (Basic → Advanced)
 *       All Stream methods, types, collectors covered
 * ============================================================
 */
public class Java8StreamsMasterClass {

    record Employee(String name, String dept, double salary, int age, String city) {}
    record Product(String name, String category, double price, int quantity) {}
    record Order(String orderId, String customer, double amount, String status) {}

    public static void main(String[] args) {

        // ══════════════════════════════════════
        //  SECTION 1: CREATING STREAMS
        // ══════════════════════════════════════

        // Problem 1: Stream from a List
        List<String> fruits = List.of("Apple", "Banana", "Cherry", "Date", "Elderberry");
        fruits.stream().forEach(f -> System.out.print(f + " "));
        // OUTPUT: Apple Banana Cherry Date Elderberry

        // Problem 2: Stream from an Array
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        Arrays.stream(colors).forEach(c -> System.out.print(c + " "));
        // OUTPUT: Red Green Blue Yellow

        // Problem 3: Stream.of() with direct values
        Stream.of(10, 20, 30, 40, 50).forEach(n -> System.out.print(n + " "));
        // OUTPUT: 10 20 30 40 50

        // Problem 4: Stream from a Set
        Set<Integer> numSet = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        numSet.stream().sorted().forEach(n -> System.out.print(n + " "));
        // OUTPUT: 1 2 3 4 5

        // Problem 5: IntStream.range() — excludes end value
        IntStream.range(1, 6).forEach(n -> System.out.print(n + " "));
        // OUTPUT: 1 2 3 4 5

        // Problem 6: IntStream.rangeClosed() — includes end value
        IntStream.rangeClosed(1, 5).forEach(n -> System.out.print(n + " "));
        // OUTPUT: 1 2 3 4 5

        // Problem 7: Stream.iterate() — infinite stream, use limit()
        Stream.iterate(3, n -> n + 3).limit(5).forEach(n -> System.out.print(n + " "));
        // OUTPUT: 3 6 9 12 15

        // Problem 8: Stream.generate() — infinite stream, use limit()
        Stream.generate(Math::random).limit(5)
              .map(d -> String.format("%.2f", d))
              .forEach(n -> System.out.print(n + " "));
        // OUTPUT: 0.73 0.12 0.45 0.89 0.31   <- (random each run)

        // Problem 9: Stream.empty() — creates an empty stream
        long emptyCount = Stream.empty().count();
        System.out.println(emptyCount);
        // OUTPUT: 0

        // Problem 10: Stream from Map entrySet
        Map<String, Integer> scores = Map.of("Alice", 90, "Bob", 75, "Charlie", 85);
        scores.entrySet().stream()
              .sorted(Map.Entry.comparingByKey())
              .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
        // OUTPUT:
        // Alice -> 90
        // Bob -> 75
        // Charlie -> 85


        // ══════════════════════════════════════
        //  SECTION 2: FILTER
        // ══════════════════════════════════════

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

        // Problem 11: Filter even numbers
        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evens);
        // OUTPUT: [2, 4, 6, 8, 10, 12]

        // Problem 12: Filter odd numbers
        List<Integer> odds = numbers.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        System.out.println(odds);
        // OUTPUT: [1, 3, 5, 7, 9, 11]

        // Problem 13: Filter numbers greater than 5
        numbers.stream().filter(n -> n > 5).forEach(n -> System.out.print(n + " "));
        // OUTPUT: 6 7 8 9 10 11 12

        // Problem 14: Filter strings starting with 'A'
        List<String> names = List.of("Alice", "Bob", "Anna", "Brian", "Andy", "Charlie");
        names.stream().filter(n -> n.startsWith("A")).forEach(n -> System.out.print(n + " "));
        // OUTPUT: Alice Anna Andy

        // Problem 15: Filter strings with length > 4
        names.stream().filter(n -> n.length() > 4).forEach(n -> System.out.print(n + " "));
        // OUTPUT: Alice Brian Charlie

        // Problem 16: Filter non-null values — use Objects::nonNull
        List<String> withNulls = Arrays.asList("Alice", null, "Bob", null, "Charlie");
        withNulls.stream().filter(Objects::nonNull).forEach(n -> System.out.print(n + " "));
        // OUTPUT: Alice Bob Charlie

        // Problem 17: Filter with multiple AND conditions
        numbers.stream().filter(n -> n % 2 == 0 && n % 3 == 0).forEach(n -> System.out.print(n + " "));
        // OUTPUT: 6 12

        // Problem 18: Filter with OR condition
        numbers.stream().filter(n -> n < 3 || n > 9).forEach(n -> System.out.print(n + " "));
        // OUTPUT: 1 2 10 11 12

        // Problem 19: Filter Employee objects by department
        List<Employee> employees = getEmployees();
        employees.stream()
                 .filter(e -> e.dept().equals("IT"))
                 .forEach(e -> System.out.println(e.name() + " - $" + e.salary()));
        // OUTPUT:
        // Alice - $95000.0
        // Charlie - $112000.0
        // George - $78000.0
        // Julia - $130000.0

        // Problem 20: Filter employees with salary > 80000
        employees.stream()
                 .filter(e -> e.salary() > 80000)
                 .forEach(e -> System.out.println(e.name() + " - $" + e.salary()));
        // OUTPUT:
        // Alice - $95000.0
        // Charlie - $112000.0
        // Diana - $88000.0
        // Fiona - $95000.0
        // Ivan - $105000.0
        // Julia - $130000.0


        // ══════════════════════════════════════
        //  SECTION 3: MAP / TRANSFORM
        // ══════════════════════════════════════

        // Problem 21: Convert strings to uppercase
        names.stream().map(String::toUpperCase).forEach(n -> System.out.print(n + " "));
        // OUTPUT: ALICE BOB ANNA BRIAN ANDY CHARLIE

        // Problem 22: Convert strings to lowercase
        names.stream().map(String::toLowerCase).forEach(n -> System.out.print(n + " "));
        // OUTPUT: alice bob anna brian andy charlie

        // Problem 23: Get length of each string
        names.stream().map(String::length).forEach(n -> System.out.print(n + " "));
        // OUTPUT: 5 3 4 5 4 7

        // Problem 24: Square each number
        List.of(1, 2, 3, 4, 5).stream().map(n -> n * n).forEach(n -> System.out.print(n + " "));
        // OUTPUT: 1 4 9 16 25

        // Problem 25: Double each number
        numbers.stream().map(n -> n * 2).forEach(n -> System.out.print(n + " "));
        // OUTPUT: 2 4 6 8 10 12 14 16 18 20 22 24

        // Problem 26: Extract a single field from object using method reference
        employees.stream().map(Employee::name).forEach(n -> System.out.print(n + " "));
        // OUTPUT: Alice Bob Charlie Diana Edward Fiona George Hannah Ivan Julia

        // Problem 27: Convert Integer list to String list
        List<String> numStrings = numbers.stream().map(String::valueOf).collect(Collectors.toList());
        System.out.println(numStrings);
        // OUTPUT: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]

        // Problem 28: mapToInt — sum of string lengths (avoids boxing overhead)
        int totalChars = names.stream().mapToInt(String::length).sum();
        System.out.println(totalChars);
        // OUTPUT: 28

        // Problem 29: mapToDouble — total salary of all employees
        double totalSalary = employees.stream().mapToDouble(Employee::salary).sum();
        System.out.println(totalSalary);
        // OUTPUT: 903000.0

        // Problem 30: mapToLong — sum of all numbers as long
        long totalSum = numbers.stream().mapToLong(Integer::longValue).sum();
        System.out.println(totalSum);
        // OUTPUT: 78

        // Problem 31: map to add prefix and suffix
        names.stream().map(n -> "Hello, " + n + "!").forEach(System.out::println);
        // OUTPUT:
        // Hello, Alice!
        // Hello, Bob!
        // Hello, Anna!
        // Hello, Brian!
        // Hello, Andy!
        // Hello, Charlie!

        // Problem 32: map to transform object field — apply 10% salary raise
        employees.stream()
                 .map(e -> e.name() + " -> $" + String.format("%.2f", e.salary() * 1.10))
                 .forEach(System.out::println);
        // OUTPUT:
        // Alice -> $104500.00
        // Bob -> $74800.00
        // Charlie -> $123200.00
        // Diana -> $96800.00
        // Edward -> $79200.00
        // Fiona -> $104500.00
        // George -> $85800.00
        // Hannah -> $66000.00
        // Ivan -> $115500.00
        // Julia -> $143000.00


        // ══════════════════════════════════════
        //  SECTION 4: FLATMAP
        // ══════════════════════════════════════

        // Problem 33: Flatten List<List<Integer>> into single flat List
        List<List<Integer>> nested = List.of(List.of(1, 2, 3), List.of(4, 5), List.of(6, 7, 8));
        List<Integer> flat = nested.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(flat);
        // OUTPUT: [1, 2, 3, 4, 5, 6, 7, 8]

        // Problem 34: Flatten List of String arrays into single List
        List<String[]> strArrays = List.of(
            new String[]{"a", "b"}, new String[]{"c", "d"}, new String[]{"e"}
        );
        List<String> flatStrs = strArrays.stream().flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println(flatStrs);
        // OUTPUT: [a, b, c, d, e]

        // Problem 35: Split sentences into distinct sorted words
        List<String> sentences = List.of("Hello World", "Java Streams", "Hello Java");
        List<String> words = sentences.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(words);
        // OUTPUT: [Hello, Java, Streams, World]

        // Problem 36: flatMap with Optional — filter out empty Optionals
        List<Optional<String>> optionals = List.of(
            Optional.of("Alice"), Optional.empty(), Optional.of("Bob"), Optional.empty()
        );
        List<String> present = optionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println(present);
        // OUTPUT: [Alice, Bob]

        // Problem 37: Distinct sorted values extracted from object field
        List<String> cities = employees.stream()
                .map(Employee::city)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(cities);
        // OUTPUT: [Boston, Chicago, New York]

        // Problem 38: Flatten Map<String, List<String>> values into one list
        Map<String, List<String>> deptSkills = Map.of(
            "IT",      List.of("Java", "Python", "SQL"),
            "HR",      List.of("Excel", "SAP"),
            "Finance", List.of("Excel", "Python")
        );
        List<String> allSkills = deptSkills.values().stream()
                .flatMap(Collection::stream)
                .distinct().sorted()
                .collect(Collectors.toList());
        System.out.println(allSkills);
        // OUTPUT: [Excel, Java, Python, SAP, SQL]


        // ══════════════════════════════════════
        //  SECTION 5: SORTED / DISTINCT / LIMIT / SKIP
        // ══════════════════════════════════════

        // Problem 39: Sort integers natural order (ascending)
        List.of(5, 3, 1, 4, 2).stream().sorted().forEach(n -> System.out.print(n + " "));
        // OUTPUT: 1 2 3 4 5

        // Problem 40: Sort integers descending using Comparator.reverseOrder()
        List.of(5, 3, 1, 4, 2).stream()
                .sorted(Comparator.reverseOrder())
                .forEach(n -> System.out.print(n + " "));
        // OUTPUT: 5 4 3 2 1

        // Problem 41: Sort strings by their character length
        names.stream()
                .sorted(Comparator.comparingInt(String::length))
                .forEach(n -> System.out.print(n + " "));
        // OUTPUT: Bob Anna Andy Alice Brian Charlie

        // Problem 42: Sort employee objects by salary descending
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::salary).reversed())
                .forEach(e -> System.out.println(e.name() + " $" + e.salary()));
        // OUTPUT:
        // Julia $130000.0
        // Charlie $112000.0
        // Ivan $105000.0
        // Alice $95000.0
        // Fiona $95000.0
        // Diana $88000.0
        // George $78000.0
        // Edward $72000.0
        // Bob $68000.0
        // Hannah $60000.0

        // Problem 43: distinct() removes duplicate values automatically
        List<Integer> dups = List.of(1, 2, 2, 3, 3, 3, 4, 5, 5);
        dups.stream().distinct().forEach(n -> System.out.print(n + " "));
        // OUTPUT: 1 2 3 4 5

        // Problem 44: limit(n) — take only first n elements
        fruits.stream().limit(3).forEach(f -> System.out.print(f + " "));
        // OUTPUT: Apple Banana Cherry

        // Problem 45: skip(n) — skip first n elements, take the rest
        fruits.stream().skip(2).forEach(f -> System.out.print(f + " "));
        // OUTPUT: Cherry Date Elderberry

        // Problem 46: Pagination using skip() + limit() pattern
        int page = 2, pageSize = 3;
        numbers.stream()
                .skip((long)(page - 1) * pageSize)
                .limit(pageSize)
                .forEach(n -> System.out.print(n + " "));
        // OUTPUT: 4 5 6   <- page 2, size 3 means skip(3) then limit(3)


        // ══════════════════════════════════════
        //  SECTION 6: REDUCE
        // ══════════════════════════════════════

        // Problem 47: Sum all elements using reduce with identity 0
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        // OUTPUT: 78

        // Problem 48: Product of 1..5 using reduce with identity 1
        int product = List.of(1, 2, 3, 4, 5).stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);
        // OUTPUT: 120

        // Problem 49: Max value using reduce — returns Optional (no identity)
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(max.orElse(-1));
        // OUTPUT: 12

        // Problem 50: Min value using reduce — returns Optional (no identity)
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        System.out.println(min.orElse(-1));
        // OUTPUT: 1

        // Problem 51: Concatenate all strings using reduce
        String concat = names.stream().reduce("", (a, b) -> a.isEmpty() ? b : a + ", " + b);
        System.out.println(concat);
        // OUTPUT: Alice, Bob, Anna, Brian, Andy, Charlie

        // Problem 52: Count total words across all sentences using reduce
        int wordCount = sentences.stream()
                .map(s -> s.split(" ").length)
                .reduce(0, Integer::sum);
        System.out.println(wordCount);
        // OUTPUT: 6

        // Problem 53: Find highest-paid Employee object using reduce
        Optional<Employee> highestPaid = employees.stream()
                .reduce((a, b) -> a.salary() > b.salary() ? a : b);
        highestPaid.ifPresent(e -> System.out.println(e.name() + " $" + e.salary()));
        // OUTPUT: Julia $130000.0


        // ══════════════════════════════════════
        //  SECTION 7: COLLECTORS
        // ══════════════════════════════════════

        // Problem 54: Collect filtered stream result to List
        List<Integer> evenList = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenList);
        // OUTPUT: [2, 4, 6, 8, 10, 12]

        // Problem 55: Collect to Set — automatically removes duplicates
        Set<String> nameSet = names.stream().collect(Collectors.toSet());
        System.out.println(nameSet.size());
        // OUTPUT: 6   <- order not guaranteed in Set

        // Problem 56: Join strings with delimiter, prefix, and suffix
        String joined = names.stream().collect(Collectors.joining(", ", "[", "]"));
        System.out.println(joined);
        // OUTPUT: [Alice, Bob, Anna, Brian, Andy, Charlie]

        // Problem 57: Count matching elements using Collectors.counting()
        long itCount = employees.stream()
                .filter(e -> e.dept().equals("IT"))
                .collect(Collectors.counting());
        System.out.println(itCount);
        // OUTPUT: 4

        // Problem 58: groupingBy — group by field into Map<key, List<value>>
        Map<String, List<Employee>> byDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept));
        byDept.forEach((dept, emps) -> {
            System.out.println(dept + ":");
            emps.forEach(e -> System.out.println("  - " + e.name()));
        });
        // OUTPUT:
        // Finance:
        //   - Diana
        //   - Fiona
        //   - Ivan
        // HR:
        //   - Bob
        //   - Edward
        //   - Hannah
        // IT:
        //   - Alice
        //   - Charlie
        //   - George
        //   - Julia

        // Problem 59: groupingBy + counting() — count per group
        Map<String, Long> countByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept, Collectors.counting()));
        countByDept.forEach((d, c) -> System.out.println(d + ": " + c));
        // OUTPUT:
        // Finance: 3
        // HR: 3
        // IT: 4

        // Problem 60: groupingBy + averagingDouble — average numeric value per group
        Map<String, Double> avgByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept,
                        Collectors.averagingDouble(Employee::salary)));
        avgByDept.forEach((d, avg) -> System.out.printf("%s: $%.2f%n", d, avg));
        // OUTPUT:
        // Finance: $96000.00
        // HR: $66666.67
        // IT: $103750.00

        // Problem 61: groupingBy + summingDouble — total numeric value per group
        Map<String, Double> totalByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept,
                        Collectors.summingDouble(Employee::salary)));
        totalByDept.forEach((d, t) -> System.out.printf("%s: $%.2f%n", d, t));
        // OUTPUT:
        // Finance: $288000.00
        // HR: $200000.00
        // IT: $415000.00

        // Problem 62: partitioningBy — splits stream into true/false Map
        Map<Boolean, List<Employee>> partitioned = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.salary() > 80000));
        System.out.println("High: " + partitioned.get(true).stream().map(Employee::name).collect(Collectors.toList()));
        System.out.println("Low:  " + partitioned.get(false).stream().map(Employee::name).collect(Collectors.toList()));
        // OUTPUT:
        // High: [Alice, Charlie, Diana, Fiona, Ivan, Julia]
        // Low:  [Bob, Edward, George, Hannah]

        // Problem 63: toMap — collect stream into Map<keyField, valueField>
        Map<String, Double> nameToSalary = employees.stream()
                .collect(Collectors.toMap(Employee::name, Employee::salary));
        nameToSalary.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + " -> $" + e.getValue()));
        // OUTPUT:
        // Alice -> $95000.0
        // Bob -> $68000.0
        // Charlie -> $112000.0
        // Diana -> $88000.0
        // Edward -> $72000.0
        // Fiona -> $95000.0
        // George -> $78000.0
        // Hannah -> $60000.0
        // Ivan -> $105000.0
        // Julia -> $130000.0

        // Problem 64: summarizingInt — count/sum/min/max/avg all in one call
        IntSummaryStatistics ageStat = employees.stream()
                .collect(Collectors.summarizingInt(Employee::age));
        System.out.println(ageStat);
        // OUTPUT: IntSummaryStatistics{count=10, sum=367, min=26, average=36.700000, max=50}

        // Problem 65: collectingAndThen — post-process the collected result
        List<String> unmodifiable = names.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(), Collections::unmodifiableList));
        System.out.println(unmodifiable.size());
        // OUTPUT: 6

        // Problem 66: groupingBy + maxBy — find max element in each group
        Map<String, Optional<Employee>> topByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary))));
        topByDept.forEach((d, e) -> e.ifPresent(emp ->
                System.out.println(d + ": " + emp.name() + " $" + emp.salary())));
        // OUTPUT:
        // Finance: Ivan $105000.0
        // HR: Edward $72000.0
        // IT: Julia $130000.0

        // Problem 67: toUnmodifiableList — immutable list (Java 10+)
        List<String> unmodList = names.stream().collect(Collectors.toUnmodifiableList());
        System.out.println(unmodList);
        // OUTPUT: [Alice, Bob, Anna, Brian, Andy, Charlie]


        // ══════════════════════════════════════
        //  SECTION 8: MATCH / FIND / COUNT
        // ══════════════════════════════════════

        // Problem 68: anyMatch — true if ANY element satisfies predicate
        boolean anyA = names.stream().anyMatch(n -> n.startsWith("A"));
        System.out.println(anyA);
        // OUTPUT: true

        // Problem 69: allMatch — true only if ALL elements satisfy predicate
        boolean allPos = numbers.stream().allMatch(n -> n > 0);
        System.out.println(allPos);
        // OUTPUT: true

        // Problem 70: noneMatch — true if NO element satisfies predicate
        boolean noneNeg = numbers.stream().noneMatch(n -> n < 0);
        System.out.println(noneNeg);
        // OUTPUT: true

        // Problem 71: findFirst — returns first matching element as Optional
        Optional<String> firstB = names.stream().filter(n -> n.startsWith("B")).findFirst();
        System.out.println(firstB.orElse("Not found"));
        // OUTPUT: Bob

        // Problem 72: findAny — returns any matching element (best for parallel)
        Optional<Employee> anyHR = employees.stream().filter(e -> e.dept().equals("HR")).findAny();
        anyHR.ifPresent(e -> System.out.println(e.name()));
        // OUTPUT: Bob   <- may vary in parallel stream

        // Problem 73: count — count total elements matching a filter
        long financeCount = employees.stream().filter(e -> e.dept().equals("Finance")).count();
        System.out.println(financeCount);
        // OUTPUT: 3

        // Problem 74: min with Comparator — finds minimum element in stream
        Optional<Employee> youngest = employees.stream()
                .min(Comparator.comparingInt(Employee::age));
        youngest.ifPresent(e -> System.out.println(e.name() + " age " + e.age()));
        // OUTPUT: Hannah age 26

        // Problem 75: max with Comparator — finds maximum element in stream
        Optional<Employee> oldest = employees.stream()
                .max(Comparator.comparingInt(Employee::age));
        oldest.ifPresent(e -> System.out.println(e.name() + " age " + e.age()));
        // OUTPUT: George age 50

        // Problem 76: count + filter with range condition
        long midRange = employees.stream()
                .filter(e -> e.salary() >= 70000 && e.salary() <= 100000).count();
        System.out.println(midRange);
        // OUTPUT: 5


        // ══════════════════════════════════════
        //  SECTION 9: NUMERIC STREAMS
        // ══════════════════════════════════════

        // Problem 77: IntStream.rangeClosed — sum of 1 to 100 (Gauss formula = 5050)
        int sum100 = IntStream.rangeClosed(1, 100).sum();
        System.out.println(sum100);
        // OUTPUT: 5050

        // Problem 78: average() — returns OptionalDouble for mean of stream
        OptionalDouble avg = numbers.stream().mapToInt(Integer::intValue).average();
        System.out.printf("%.2f%n", avg.getAsDouble());
        // OUTPUT: 6.50

        // Problem 79: summaryStatistics — count/sum/min/max/avg all at once
        IntSummaryStatistics stats = IntStream.rangeClosed(1, 10).summaryStatistics();
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum:   " + stats.getSum());
        System.out.println("Min:   " + stats.getMin());
        System.out.println("Max:   " + stats.getMax());
        System.out.printf("Avg:   %.2f%n", stats.getAverage());
        // OUTPUT:
        // Count: 10
        // Sum:   55
        // Min:   1
        // Max:   10
        // Avg:   5.50

        // Problem 80: Fibonacci sequence using Stream.iterate on a pair array
        Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
              .limit(10).map(f -> f[0]).forEach(f -> System.out.print(f + " "));
        // OUTPUT: 0 1 1 2 3 5 8 13 21 34

        // Problem 81: Even numbers 1-20 using IntStream.filter
        IntStream.rangeClosed(1, 20).filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        // OUTPUT: 2 4 6 8 10 12 14 16 18 20

        // Problem 82: boxed() converts primitive IntStream -> Stream<Integer>
        List<Integer> boxedList = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());
        System.out.println(boxedList);
        // OUTPUT: [1, 2, 3, 4, 5]

        // Problem 83: LongStream — handles very large number sums without overflow
        long bigSum = LongStream.rangeClosed(1, 1000).sum();
        System.out.println(bigSum);
        // OUTPUT: 500500

        // Problem 84: DoubleStream average — decimal average of product prices
        List<Product> products = getProducts();
        OptionalDouble avgPrice = products.stream().mapToDouble(Product::price).average();
        System.out.printf("$%.2f%n", avgPrice.getAsDouble());
        // OUTPUT: $423.99


        // ══════════════════════════════════════
        //  SECTION 10: OPTIONAL
        // ══════════════════════════════════════

        // Problem 85: orElse — returns default value if Optional is empty
        Optional<String> opt = Optional.empty();
        System.out.println(opt.orElse("Default Name"));
        // OUTPUT: Default Name

        // Problem 86: orElseGet — lazily computes default value via Supplier
        Optional<Integer> emptyInt = Optional.empty();
        int val = emptyInt.orElseGet(() -> 42);
        System.out.println(val);
        // OUTPUT: 42

        // Problem 87: Optional.map — transform value if present, skip if empty
        Optional<String> name = Optional.of("alice");
        Optional<String> upper = name.map(String::toUpperCase);
        System.out.println(upper.orElse("empty"));
        // OUTPUT: ALICE

        // Problem 88: Optional.filter — retain value only if predicate passes
        Optional<Integer> filtered = Optional.of(15).filter(n -> n > 10);
        System.out.println(filtered.isPresent());
        // OUTPUT: true


        // ══════════════════════════════════════
        //  SECTION 11: PEEK (DEBUGGING)
        // ══════════════════════════════════════

        // Problem 89: peek — inspect each element at any stage without consuming the stream
        List<Integer> result = List.of(1, 2, 3, 4, 5).stream()
                .peek(n -> System.out.print("Input:" + n + " | "))
                .filter(n -> n % 2 != 0)
                .peek(n -> System.out.print("Kept:" + n + " | "))
                .map(n -> n * n)
                .peek(n -> System.out.println("Squared:" + n))
                .collect(Collectors.toList());
        System.out.println("Final: " + result);
        // OUTPUT:
        // Input:1 | Kept:1 | Squared:1
        // Input:2 |
        // Input:3 | Kept:3 | Squared:9
        // Input:4 |
        // Input:5 | Kept:5 | Squared:25
        // Final: [1, 9, 25]


        // ══════════════════════════════════════
        //  SECTION 12: PARALLEL STREAMS
        // ══════════════════════════════════════

        // Problem 90: parallelStream — uses multiple CPU cores automatically
        long parallelSum = LongStream.rangeClosed(1, 1_000_000).parallel().sum();
        System.out.println(parallelSum);
        // OUTPUT: 500000500000

        // Problem 91: sequential vs parallel timing comparison
        List<Integer> bigList = IntStream.rangeClosed(1, 500_000).boxed().collect(Collectors.toList());

        long start = System.currentTimeMillis();
        bigList.stream().mapToLong(Integer::longValue).sum();
        System.out.println("Sequential: " + (System.currentTimeMillis() - start) + "ms");
        // OUTPUT: Sequential: ~10ms   <- varies by machine

        start = System.currentTimeMillis();
        bigList.parallelStream().mapToLong(Integer::longValue).sum();
        System.out.println("Parallel:   " + (System.currentTimeMillis() - start) + "ms");
        // OUTPUT: Parallel:   ~4ms   <- faster on multi-core machines


        // ══════════════════════════════════════
        //  SECTION 13: REAL-WORLD PROBLEMS
        // ══════════════════════════════════════

        // Problem 92: Top 3 highest paid employees — sorted + limit
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::salary).reversed())
                .limit(3)
                .forEach(e -> System.out.println(e.name() + " $" + e.salary()));
        // OUTPUT:
        // Julia $130000.0
        // Charlie $112000.0
        // Ivan $105000.0

        // Problem 93: Employees earning above average salary
        double avgSalary = employees.stream().mapToDouble(Employee::salary).average().orElse(0);
        employees.stream()
                .filter(e -> e.salary() > avgSalary)
                .forEach(e -> System.out.println(e.name() + " $" + e.salary()));
        // OUTPUT:  (avg = $90300.0, only those above it shown)
        // Charlie $112000.0
        // Fiona $95000.0
        // Ivan $105000.0
        // Julia $130000.0

        // Problem 94: Cheapest product in each category using groupingBy + minBy
        products.stream()
                .collect(Collectors.groupingBy(Product::category,
                        Collectors.minBy(Comparator.comparingDouble(Product::price))))
                .forEach((cat, p) -> p.ifPresent(prod ->
                        System.out.printf("%s: %s $%.2f%n", cat, prod.name(), prod.price())));
        // OUTPUT:
        // Electronics: Mouse $49.99
        // Furniture: Bookshelf $149.99

        // Problem 95: Word frequency — groupingBy + counting, sorted by frequency
        List<String> text = List.of("the cat sat on the mat the cat");
        Map<String, Long> wordFreq = text.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        wordFreq.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
        // OUTPUT:
        // the: 3
        // cat: 2
        // sat: 1
        // on: 1
        // mat: 1

        // Problem 96: Deduplicate + sort + join with custom delimiter
        List<String> messy = List.of("Java", "Python", "Java", "Go", "Python", "Kotlin");
        String cleaned = messy.stream().distinct().sorted().collect(Collectors.joining(" | "));
        System.out.println(cleaned);
        // OUTPUT: Go | Java | Kotlin | Python

        // Problem 97: Nested groupingBy — department -> city -> count
        Map<String, Map<String, Long>> nestedGroup = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept,
                        Collectors.groupingBy(Employee::city, Collectors.counting())));
        nestedGroup.forEach((dept, cityMap) -> {
            System.out.println(dept + ":");
            cityMap.forEach((city, count) -> System.out.println("  " + city + ": " + count));
        });
        // OUTPUT:
        // Finance:
        //   Chicago: 1
        //   New York: 2
        // HR:
        //   Boston: 1
        //   Chicago: 2
        // IT:
        //   Boston: 1
        //   New York: 2

        // Problem 98: Check if a list is sorted using IntStream.range + allMatch
        List<Integer> sortedList   = List.of(1, 2, 3, 4, 5);
        List<Integer> unsortedList = List.of(1, 3, 2, 4, 5);
        boolean isSorted   = IntStream.range(0, sortedList.size() - 1)
                .allMatch(i -> sortedList.get(i) <= sortedList.get(i + 1));
        boolean isUnsorted = IntStream.range(0, unsortedList.size() - 1)
                .allMatch(i -> unsortedList.get(i) <= unsortedList.get(i + 1));
        System.out.println("[1,2,3,4,5] sorted? " + isSorted);
        System.out.println("[1,3,2,4,5] sorted? " + isUnsorted);
        // OUTPUT:
        // [1,2,3,4,5] sorted? true
        // [1,3,2,4,5] sorted? false

        // Problem 99: Second highest salary — distinct + sorted desc + skip(1) + findFirst
        Optional<Double> secondHighest = employees.stream()
                .map(Employee::salary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println("2nd highest: $" + secondHighest.orElse(0.0));
        // OUTPUT: 2nd highest: $112000.0

        // Problem 100: Group anagrams — sort each word's chars as the grouping key
        List<String> wordList = List.of("eat", "tea", "tan", "ate", "nat", "bat");
        Map<String, List<String>> anagrams = wordList.stream()
                .collect(Collectors.groupingBy(w -> {
                    char[] ch = w.toCharArray();
                    Arrays.sort(ch);
                    return new String(ch);
                }));
        anagrams.forEach((key, group) -> System.out.println(group));
        // OUTPUT:
        // [eat, tea, ate]
        // [tan, nat]
        // [bat]

        System.out.println("\n=== ALL 100 PROBLEMS DONE — YOU ARE A STREAM PRO NOW! ===");
    }

    // ─────────────────────────────────────────────────────────
    //  DATA HELPERS
    // ─────────────────────────────────────────────────────────
    static List<Employee> getEmployees() {
        return List.of(
            new Employee("Alice",   "IT",       95000, 30, "New York"),
            new Employee("Bob",     "HR",       68000, 45, "Chicago"),
            new Employee("Charlie", "IT",      112000, 35, "New York"),
            new Employee("Diana",   "Finance",  88000, 40, "Chicago"),
            new Employee("Edward",  "HR",       72000, 28, "Boston"),
            new Employee("Fiona",   "Finance",  95000, 33, "New York"),
            new Employee("George",  "IT",       78000, 50, "Boston"),
            new Employee("Hannah",  "HR",       60000, 26, "Chicago"),
            new Employee("Ivan",    "Finance", 105000, 38, "New York"),
            new Employee("Julia",   "IT",      130000, 42, "Boston")
        );
    }

    static List<Product> getProducts() {
        return List.of(
            new Product("Laptop",    "Electronics", 999.99, 50),
            new Product("Phone",     "Electronics", 699.99, 120),
            new Product("Tablet",    "Electronics", 499.99, 80),
            new Product("Chair",     "Furniture",   199.99, 30),
            new Product("Desk",      "Furniture",   349.99, 20),
            new Product("Monitor",   "Electronics", 399.99, 60),
            new Product("Bookshelf", "Furniture",   149.99, 15),
            new Product("Keyboard",  "Electronics",  89.99, 200),
            new Product("Sofa",      "Furniture",   799.99, 10),
            new Product("Mouse",     "Electronics",  49.99, 250)
        );
    }
}

