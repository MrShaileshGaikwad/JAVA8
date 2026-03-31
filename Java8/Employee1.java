package Java8;

public class Employee1 {

	    String name;
	    String dept;
	    int salary;

	    Employee1(String name, String dept, int salary) {
	        this.name = name;
	        this.dept = dept;
	        this.salary = salary;
	    }

	    public String getName() { return name; }
	    public String getDept() { return dept; }
	    public int getSalary() { return salary; }

	    @Override
	    public String toString() {
	        return name + "-" + dept + "-" + salary;
	    }
	}
