package vmzona;

import java.util.Set;
import java.util.TreeSet;

public class Guest {
	 
	private Set<Stoka> stokiVKolichka = new TreeSet<>();
	private static int sumOrder = 0;
	 
	
	 public void buySomething(Stoka st) throws Exception {
	        if (st != null) {
	             this.stokiVKolichka.add(st);
	             this.sumOrder += st.getCena();
	        } else {
	            System.out.println("Stoka is null!");
	        }
	 }
	 
	 public void listAllOrders() {
	        for (Stoka st : this.stokiVKolichka) {
	            System.out.println(st);
	        }
	        System.out.print(this.getSumOrder());
	    }

	public static int getSumOrder() {
		return sumOrder;
	}
}
