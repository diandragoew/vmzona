package vmzona;

import java.util.List;
import java.util.Map;

public class Worker extends Paraliya {
	
	private int otpisaniStoki = 0;

	public Worker(String name, int salary) {
		super(name, salary);
	}

	public void otpishi(Map<Stoka, Integer> stoki) {
		for (Stoka stoka : stoki.keySet()) {
			this.otpisaniStoki++;
			System.out.println("Az sym rabotnika " + this.getName());
			System.out.println("Shte otpisha tazi stoka ot sklada" + stoka.getIme());
		}
	}
	
	public int getOtpisaniStoki() {
		return otpisaniStoki;
	}

	public void setPOtpisaniStoki(int otpisaniStoki) {
		this.otpisaniStoki = otpisaniStoki;
	}

	@Override
	public String toString() {
		return "Worker [Name = " + getName() + ", Salary = " + getSalary() + "]";
	}
}
