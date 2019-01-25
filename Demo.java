import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Demo {

	public static final int BROI_ARTIKULI = 100;

    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Map<String, User> users = new HashMap<String, User>();
		users.put("gosho@abv.bg", new User("gosho@abv.bg", "marola"));
		
		Vmzona magazin = new Vmzona("Vmzona");
        magazin.zarediSe();
        
        System.out.println("MENU");
		System.out.println ("--------");
		System.out.println("1.Guest");
		System.out.println("2.User");
		System.out.println("3.Administrator");
		System.out.println("4.Exit");
        
        int choose = sc.nextInt();
        if(choose == 1) {
        	
        	magazin.izkaraiVsichkiNalichniStoki();
        }
        if(choose == 2){
        	
        	System.out.println("1.New User");
    		System.out.println("2.Exist User");
    		
    		System.out.println("izbor");
    		int choose1 = sc.nextInt(); 
    		
    		if(choose1 == 1) {
    			
        		String email = sc.nextLine();
        		System.out.println("pas");
        		String password = sc.nextLine();
        		
            	users.put(email, new User(email, password));
            }
            else {
            	
            	String email1 = sc.nextLine();
            	System.out.println("pas");
            	String password1 = sc.nextLine();
            	
            	User user = users.get(email1);
        		if (user == null || !user.getPassword().equals(password1)) {
        			
        			System.out.println("Sorry, wrong email or password!");
        		} 
        		else {
        			magazin.izkaraiVsichkiNalichniStoki();
        		}
            }
        }
		if(choose == 3){
			String emailAdministrator = "strogoSecretno@abv.bg";
			String checkEmail = sc.nextLine();
			if(checkEmail.equals(emailAdministrator)){
				//TODO...
			}
			else{
				System.out.println("You have not access to this information!");
			}
		}
		
		if(choose == 4){
		
		}
	}

}

