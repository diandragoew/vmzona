package vmzona;

import java.util.Map;
import java.util.Random;

public class Dostavchik{

	private static final int MAX_SALARY = 1200;
	private static final int MIN_SALARY = 800;
	private String name;
	private int salary;
	
	public Dostavchik(String name){
		this.name = name;
		this.salary = new Random().nextInt(MAX_SALARY - MIN_SALARY + 1) + MIN_SALARY;
	}
	
	@Override
	public String toString() {
		return "[<Name -> " + name + "> / <Salary -> " + salary + " lv.>]";
	}

	public String getName() {
		return name;
	}
}