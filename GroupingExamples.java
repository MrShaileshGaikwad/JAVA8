package com.stream.GroupingBy;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;


//namesByAge: {40=[dave], 25=[bob, eve], 30=[carol, alice]}
//avgSalary: {HR=60000.0, SALES=73000.0, ENG=78000.0}
//topPerDept: {HR=Optional[Ann(HR,age=40,$60000.0)], SALES=Optional[Zoe(SALES,age=35,$90000.0)], ENG=Optional[Jane(ENG,age=30,$82000.0)]}

public class GroupingExamples {

    public static void main(String[] args) {
        // --- sample data ---
        List<String> words = Arrays.asList(
                "apple", "bee", "cat", "dog", "apple", "door", "elephant", "cat", "ant", "boat"
        );

        List<User> users = Arrays.asList(
                new User("alice", 30, "SALES", true),
                new User("bob", 25, "ENG", false),
                new User("carol", 30, "SALES", true),
                new User("dave", 40, "HR", false),
                new User("eve", 25, "ENG", true)
        );

        List<Employee> emps = Arrays.asList(
                new Employee("John", "ENG", 25, 75000.0),
                new Employee("Jane", "ENG", 30, 82000.0),
                new Employee("Bill", "SALES", 28, 56000.0),
                new Employee("Zoe", "SALES", 35, 90000.0),
                new Employee("Ann", "HR", 40, 60000.0),
                new Employee("Tom", "ENG", 25, 77000.0)
        );

        // 1) Map<Integer,List<String>> byLen = words.stream().collect(groupingBy(String::length));
        Map<Integer, List<String>> byLen = words.stream()
                .collect(groupingBy(String::length));
        System.out.println("byLen: " + byLen);
        //byLen: {3=[bee, cat, dog, cat, ant], 4=[door, boat], 5=[apple, apple], 8=[elephant]}

        // 2) Map<String,List<User>> byDept = users.stream().collect(groupingBy(User::getDept));
        Map<String, List<User>> byDept = users.stream()
                .collect(groupingBy(User::getDept));
        System.out.println("byDept: " + byDept);
      //byDept: {HR=[dave(40,HR,I)], SALES=[alice(30,SALES,A), carol(30,SALES,A)], ENG=[bob(25,ENG,I), eve(25,ENG,A)]}

        // 3) Map<String,Long> counts = words.stream().collect(groupingBy(w->w, counting()));
        Map<String, Long> counts = words.stream()
                .collect(groupingBy(w -> w, counting()));
        System.out.println("counts: " + counts);
////counts: {door=1, apple=2, bee=1, ant=1, cat=2, elephant=1, dog=1, boat=1} counting of element how many time
        
        // 4) Map<Boolean,List<User>> byActive = users.stream().collect(groupingBy(u->u.isActive()));
        Map<Boolean, List<User>> byActive = users.stream()
                .collect(groupingBy(User::isActive));
        System.out.println("byActive: " + byActive);
      //byActive: {false=[bob(25,ENG,I), dave(40,HR,I)], true=[alice(30,SALES,A), carol(30,SALES,A), eve(25,ENG,A)]}

        // 5) Map<Integer,Set<String>> join = users.stream().collect(groupingBy(User::getAge, mapping(User::getName, toSet())));
        Map<Integer, Set<String>> namesByAge = users.stream()
                .collect(groupingBy(User::getAge, mapping(User::getName, toSet())));
        System.out.println("namesByAge: " + namesByAge);

        // 6) Map<String,Double> avgSalary = emps.stream().collect(groupingBy(Employee::getDept, averagingDouble(Employee::getSalary)));
        Map<String, Double> avgSalary = emps.stream()
                .collect(groupingBy(Employee::getDept, averagingDouble(Employee::getSalary)));
        System.out.println("avgSalary: " + avgSalary);

        // 7) Map<String,Optional<Employee>> top = emps.stream().collect(groupingBy(Employee::getDept, maxBy(Comparator.comparing(Employee::getSalary))));
        Map<String, Optional<Employee>> topPerDept = emps.stream()
                .collect(groupingBy(Employee::getDept,
                        maxBy(Comparator.comparing(Employee::getSalary))));
        System.out.println("topPerDept: " + topPerDept);

        // 8) Map<String,List<Employee>> sortedGroup = emps.stream().sorted(Comparator.comparing(Employee::getName)).collect(groupingBy(Employee::getDept, LinkedHashMap::new, toList()));
        Map<String, List<Employee>> sortedGroup = emps.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .collect(groupingBy(Employee::getDept, LinkedHashMap::new, toList()));
        System.out.println("sortedGroup (LinkedHashMap to preserve dept order): " + sortedGroup);
      //nested grouping (dept -> age -> employees): {HR={40=[Ann(HR,age=40,$60000.0)]}, SALES={35=[Zoe(SALES,age=35,$90000.0)], 28=[Bill(SALES,age=28,$56000.0)]}, ENG={25=[John(ENG,age=25,$75000.0), Tom(ENG,age=25,$77000.0)], 30=[Jane(ENG,age=30,$82000.0)]}}
        
        // 9) Map<String,Map<Integer,List<Employee>>> nested = emps.stream().collect(groupingBy(Employee::getDept, groupingBy(Employee::getAge)));
        Map<String, Map<Integer, List<Employee>>> nested = emps.stream()
                .collect(groupingBy(Employee::getDept, groupingBy(Employee::getAge)));
        System.out.println("nested grouping (dept -> age -> employees): " + nested);
    }
    //sortedGroup (LinkedHashMap to preserve dept order): {HR=[Ann(HR,age=40,$60000.0)], SALES=[Bill(SALES,age=28,$56000.0), Zoe(SALES,age=35,$90000.0)], ENG=[Jane(ENG,age=30,$82000.0), John(ENG,age=25,$75000.0), Tom(ENG,age=25,$77000.0)]}
    
        // 10) ConcurrentMap<String,List<Employee>> concurrent = emps.parallelStream().collect(Collectors.groupingByConcurrent(Employee::getDept));
//        ConcurrentMap<String, List<Employee>> concurrent = emps.parallelStream()
//                .collect(Collectors.groupingByConcurrent(Employee::getDept));
//        System.out.println("concurrent grouping (parallel): " + concurrent);
   //concurrent grouping (parallel): {HR=[Ann(HR,age=40,$60000.0)], SALES=[Zoe(SALES,age=35,$90000.0), Bill(SALES,age=28,$56000.0)], ENG=[Jane(ENG,age=30,$82000.0), John(ENG,age=25,$75000.0), Tom(ENG,age=25,$77000.0)]}


    // --- helper classes (can be top-level public classes in their own files) ---
    static class User {
        private final String name;
        private final int age;
        private final String dept;
        private final boolean active;

        public User(String name, int age, String dept, boolean active) {
            this.name = name;
            this.age = age;
            this.dept = dept;
            this.active = active;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getDept() { return dept; }
        public boolean isActive() { return active; }

        @Override
        public String toString() {
            return name + "(" + age + "," + dept + "," + (active ? "A" : "I") + ")";
        }
    }

    static class Employee {
        private final String name;
        private final String dept;
        private final int age;
        private final double salary;

        public Employee(String name, String dept, int age, double salary) {
            this.name = name;
            this.dept = dept;
            this.age = age;
            this.salary = salary;
        }

        public String getName() { return name; }
        public String getDept() { return dept; }
        public int getAge() { return age; }
        public double getSalary() { return salary; }

        @Override
        public String toString() {
            return name + "(" + dept + ",age=" + age + ",$" + salary + ")";
        }
    }
}
