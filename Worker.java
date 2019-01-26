package vmzona;

import java.util.Map;

public class Worker extends Paraliya {

	public Worker(String name, int salary) {
		super(name, salary);
	}

	public void otpishi(Map<Stoka, Integer> stoki) {
		for (Stoka stoka : stoki.keySet()) {
			System.out.println("Az sym rabotnika " + this.getName());
			System.out.println("Shte otpisha tazi stoka ot sklada" + stoka.getIme());
		}
	}

	@Override
	public String toString() {
		return "Worker [Name = " + getName() + ", Salary = " + getSalary() + "]";
	}
}
