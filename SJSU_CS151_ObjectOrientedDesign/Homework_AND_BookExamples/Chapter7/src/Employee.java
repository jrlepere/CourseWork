import java.io.Serializable;

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int salary;
	private Department department;
	
	public Employee(String name, int salary, Department department) {
		this.name = name;
		this.salary = salary;
		this.department = department;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public Department getDepartment() {
		return department;
	}
	
	public String toString() {
		return getClass().getName() + "[name:" + name + ", salary:" + salary + ", department:" + department + "]";
	}
	
}
