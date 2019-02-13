package vmzona;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Guest {
	
	Scanner sc =  new Scanner(System.in);
	
	private Set<Stoka> stokiVKolichka = new TreeSet<>();
	private static int sumOrder = 0;
	private static Vmzona magazin;
	 
	
	public Guest() {
		this.sumOrder = 0;
	}

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
	        System.out.println("Your total sum is " + this.getSumOrder() + "$");
	        System.out.println();
	}
	 
	 public void action(Vmzona magazin) {
		 
		boolean isPlay = true;
 		while (isPlay) {
			 System.out.println("Enter a number of stock, which you want to order: ");
	         int nomerNaStoka = sc.nextInt();
	         if (magazin.proveriZaStoka(nomerNaStoka)) {
	             try {
	                 Stoka stoka = magazin.remoteStoka(nomerNaStoka);
	                 this.buySomething(stoka);
	                 magazin.addOrders(stoka);
	             }catch(Exception e) {
	             	e.printStackTrace();
	             }
	            messageForShopping();
	 	    	int choose1 = sc.nextInt();
	 	    	switch(choose1) {
		    		case 1:
		    			continue;
		    		case 2:
		    			System.out.println("List of orders:");
			    		this.listAllOrders();
			    		break; 
			    	default:
			    		System.out.println("Invalid number!");
			    		break;
	 	    	}
	         }
	         break;
		 }
	 }
	 
	public void messageForShopping() {
    	System.out.println("1.Continue with shopping.");
	    System.out.println("2.Go to shopping cart!");
    }

	public static int getSumOrder() {
		return sumOrder;
	}
}
