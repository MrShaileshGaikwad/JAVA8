package Java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Stream {
	
	public record Employee(String name , int id, int salary)
	{

//		public Employee( String name, int id, int salary) {
//			this.name = name;
//	this.id=id;
//	this.salary=salary;
//			// TODO Auto-generated constructor stub
//		}
		// Pojo implemetntation
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	List <Employee> e = new ArrayList<Employee>();
	
	e.add(new Employee("ganesh",1, 2000));
	e.add(new Employee("shailesh",4, 20000));
	e.add(new Employee("kalpesh",2, 2010));
	e.add(new Employee("yaesh",3, 1100));
	e.add(new Employee("ailesh",10, 2800));
	e.add(new Employee("shkesh",9, 2900));
	
	
	List<Integer> salary= e.stream().filter(s->s.salary>300).map(s->s.salary).collect(Collectors.toList());
			System.err.println(salary);

	}}


