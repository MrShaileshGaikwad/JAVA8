package Java8;

import java.util.*;
import java.util.stream.*;

public class EmployeeSalary {

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
            new Employee("Shailesh", 12000),
            new Employee("Lokesh", 15000),
            new Employee("Anish", 2000),
            new Employee("Kalpesh", 8000),
            new Employee("Mahesh", 112000)
        );

        List<Employee> salary = employees.stream()
                .filter(emp -> emp.getSalary() > 12000)
                .toList();

        System.out.println(salary);
    }
    
    // for the map
//    List<Map.Entry<String, Integer>> result =
//    	    h.entrySet().stream()
//    	     .filter(entry -> entry.getValue() > 10000)
//    	     .toList();
//
//    	System.out.println(result);
}