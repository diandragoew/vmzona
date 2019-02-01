package vmzona;

public class Demo {

    public static final int BROI_RAZLICHNI_STOKI = 10;
    //public static final int BROI_KATEGORII = Kategoriq.values().length;

    /*public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static int randomZaKolekcii(List<String> kolekciq) {
        return new Random().nextInt(kolekciq.size());
    }*/
    ///Когато са в Демото при мен гърмят, затова ги преметих в Stoka
    
    public static void messageForExit() {
    	
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

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	try {
    		
	        Vmzona magazin = new Vmzona("Vmzona");
	        magazin.addStoka(Kategoriq.AVTOCHASTI, new Stoka("komplekt(2 gumi)", 25, "iron", "black"));
	
	       //*********************************************************************
	        for(Kategoriq k : Kategoriq.values()) {
	        	for(int i = 1; i <= BROI_RAZLICHNI_STOKI; i++) {
	        		try {
	                    magazin.addStoka(k, Stoka.daiStoka(k));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	        	}
	        }
	        //Ne mi izkarvashe stokite pravilno, zatova go promenih
	        //*********************************************************************
	
	        System.out.println("MENU VmZona");
	        System.out.println("--------");
	        System.out.println("1 - Guest");
	        System.out.println("2 - User");
	        System.out.println("3 - Administrator");
	        System.out.println("0 - Exit");
	
	        int choose = sc.nextInt();
	        if(choose == 0) {
	        	messageForExit();
	        	return;
	        	
	        }else {
	        	boolean isPlay = true;
		        while (isPlay) {
		            System.out.println("");
		            if (choose == 0) {
		            	messageForExit();
		                isPlay = false;
		            }else {
		            	if(choose == 1) {
		            		System.out.println("Guest!");
		            		Guest guest = new Guest();
		            		magazin.izkaraiVsichkiNalichniStoki();
		            		System.out.println("Enter a number of stock, which you want to order: ");
		                    int nomerNaStoka = sc.nextInt();
		                    if (magazin.proveriZaStoka(nomerNaStoka)) {
		                        try {
		                            Stoka stoka = magazin.remoteStoka(nomerNaStoka);
		                            guest.buySomething(stoka);
		                        }catch(Exception e) {
		                        	e.printStackTrace();
		                        }
		                    }
		            		}else {
		            			System.out.println("Other!!");
		            			try {
		            				User user = User.daiMiUser(choose, "", "");
		            				user.doSomething();
		            			} catch (Exception e) {
		            			e.printStackTrace();
		            			}
		            		}
		            	
		            	}
		        	}
	        	}
    	}catch (Exception e) {

    	}
    }
}


