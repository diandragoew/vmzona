package vmzona;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Demo {

    public static final int BROI_RAZLICHNI_STOKI = 10;
    
    public static void messageForVote() {
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Are you rate our site with score of 1 to 5?");
    	System.out.println("YES or NO");
    	String chooseVote = sc.nextLine();
    	if(chooseVote.equals("YES")){
    		System.out.println("Vote:");
    		int score = sc.nextInt();
    		System.out.println("Thank you!");
    	}else {
    		System.out.println("Bye, have a nice day and come again ;)");
    	}
    }
    
    public static void messageForExit(Vmzona magazin) {
    		magazin.dostavi();
    		System.out.println("Thank you for shopping with us.");
    		System.out.println("Have a nice day! ;)");
    		System.out.println();
    		
    		System.out.println("Enter 0 for exit or number from MENU(1,2,3) for continue ");
    }
    
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	try {
    		
	        Vmzona magazin = new Vmzona("Vmzona");
	        magazin.addStoka(Kategoriq.AVTOCHASTI, new Stoka("komplekt(2 gumi)", 25, "iron", "black"));
	
	        
	        for(Kategoriq k : Kategoriq.values()) {
	        	for(int i = 1; i <= BROI_RAZLICHNI_STOKI; i++) {
	        		try {
	                    magazin.addStoka(k, Stoka.daiStoka(k));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	        	}
	        }
	
	        
	        System.out.println("MENU VmZona");
	        System.out.println("--------");
	        System.out.println("1 - Guest");
	        System.out.println("2 - User");
	        System.out.println("3 - Administrator");
	        System.out.println("0 - Exit");
	
	        int choose = sc.nextInt();
	        while(true) {
		        if(choose == 0) {
		        	messageForVote();
		        	return;
		        }else {
		        	if(choose == 1) {
	            		System.out.println("Guest!");
	            		Guest guest = new Guest();
	            		magazin.izkaraiVsichkiNalichniStoki();
	            		
	            		guest.action(magazin);
        			
            	    	System.out.println("Finished");
            	    	System.out.println("Continue");
            	    	String  st = sc.next();
            	    	if(st.equals("Finished")) {
            	    		messageForExit(magazin);
            	    		choose = sc.nextInt();
            	    	}else {
            	    		continue;
            	    	}
            	    }
		        	if(choose == 2){
            			System.out.println("Other!!");
            			try {
            				User user = new User(); /*User.daiMiUser(choose, "", "")*/;
            				user.actions(magazin);
            				
            				System.out.println("Enter 0 for exit or number from MENU(1,2,3) for continue ");
            				choose = sc.nextInt();
            				
            			} catch (Exception e) {
            				e.printStackTrace();
            			}
	            	}
		        	else {
		        		Admin admin = new Admin(magazin);
		        		admin.actions();
		        		
		        		System.out.println("Enter 0 for exit or number from MENU(1,2,3) for continue ");
        				choose = sc.nextInt();
		        	}
		            	
		        }
		    }
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}

