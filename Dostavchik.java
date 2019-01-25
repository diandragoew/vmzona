package warehouse;

import java.util.Map;

public class Dostavchik extends Paraliya {

	private static final double COEFF_PECHALBA = 0.2;
	
	public Dostavchik(String name, int salary) {
		super(name, salary);
	}
	
	public int zaredi(Map<Stoka, Integer> stoki) {
		int totalMoney = 0;
		for (Stoka stoka : stoki.keySet()) {
			totalMoney += stoka.getPrice();
		}
		
		this.increaseSalary((int)(totalMoney * COEFF_PECHALBA)); 
		return (int) (totalMoney + (totalMoney * COEFF_PECHALBA));
	}


}
