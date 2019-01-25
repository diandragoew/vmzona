package warehouse;

public class Paraliya {

	private String name;
	private int salary;
	
	public Paraliya(String name, int salary) {
		this.name = name;
		this.salary = salary;
	}
	
	public void increaseSalary(int money) {
		this.salary += money;
	}

	public String getName() {
		return name;
	}

	public int getSalary() {
		return salary;
	}

}
