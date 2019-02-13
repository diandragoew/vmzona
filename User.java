package vmzona;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 


public class User {
	
    private static final int MIN_LENGTH_OF_PASSWORD = 6;
    Scanner sc = new Scanner(System.in);
    
  //********************************************************************************************************* 
    /*public static class Admin extends User {

        public Admin(String email, String password) {
            super("strogoSecretno@abv.bg", "naidobriqadministrator28");
        }
     
       private void message() {
        	System.out.println("If you want to shopping, sing in like User!");
            System.out.println("If you want to continue like administrator, enter name and password!");
        }

        ///@Override
        public void actions(Vmzona magazin) throws Exception {
        	
        	/*this.message();
        	String desire = sc.next();
            if(desire.equals("USER")) {
            	
            	User user = new User();
            	user.actions(magazin);
            }
            
            //if(desire.equals("ADMINISTRATOR")) {
            	System.out.println("Enter your email address:");
            	String checkEmail = sc.next();
	            if (checkEmail.equals(getEmail())) {
	                System.out.println("Successful");
	                this.listOfRights();
	                int choose = sc.nextInt();
	                while(choose != 6) {
		                switch(choose) {
		                	case 1:
		                		this.premahniUser(magazin);
		                		break;
		                	case 2:
		                		this.showUsers(magazin);
		                		break;
		                	case 3:
		                		this.showOborot(magazin); //***
		                		break;
		                	case 4:
		                		this.printStocks(magazin);
		                		break;
		                	case 5:
		                		
		                		break;
		                	case 6:
		                		return;
		                	default:
		                		System.out.println("Incorrect");
		                		break;
		                }
		                choose = sc.nextInt();
	                }
	            } else {
	                throw new Exception("You have not access to this information!");
	            }
            //}
        }
        
        private void listOfRights() {
			System.out.println("1 - Remove user/s");
			System.out.println("2 - Show all users");
			System.out.println("3 - Check for turnover");
			System.out.println("4 - Show all stocks in store");
			System.out.println("5 - Show all orders in shop");
			System.out.println("6 - Exit");
		}

        private void printStocks(Vmzona magazin) {
        	magazin.izkaraiVsichkiNalichniStoki();
        }

		private void showOborot(Vmzona magazin) {
			magazin.getOborot();
		}
		
		private void showUsers(Vmzona magazin){
			int counter = 0;
			for (Map.Entry<String, User> entry : magazin.getUsers().entrySet()) {
                System.out.println("User " + counter + ": " + entry.getValue());
                System.out.println();
                counter++;
            }
		}

		private void premahniUser(Vmzona magazin) {
			int counter = 0;
            for (Map.Entry<String, User> entry : magazin.getUsers().entrySet()) {
                System.out.println("User " + counter + entry.getValue().email);
                System.out.println();
                counter++;
            }
            
            System.out.println("Enter email of user, who you want to remove:  ");
            String email = sc.next();
            if (magazin.getUsers().containsKey(email)) {
                magazin.getUsers().remove(email);
                this.showUsers(magazin);
            } 
            else {
                System.out.println("Wrong email address");
                return;
            }
        }
    }*/
    
  //*********************************************************************************************************************** 
    
    private String email;
    private String password;
    private Set<Stoka> stokiVKolichka = new TreeSet<>();
    private static int sumOrder; 
    
    private Profile ownProfile;
    
    private static Vmzona magazin;
  
    
    public User(String email, String password) {
    	this.setEmail(email);
    	try {
			this.setPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	this.ownProfile = new Profile();
    }

	public User(){
		this.sumOrder = 0;
	}
    
    private void changePassword() {
		System.out.println("Old password: ");
		String oldPassword = sc.next();
		try {
			while (oldPassword.equals(this.password)) {
				System.out.println("It's not your old password!");
				System.out.println("Try again: ");
				oldPassword = sc.next();
			}
			System.out.println("Successful!");
			String newPass = sc.next();
			this.setPassword(newPass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    }
    
    
    private void setPassword(String password) throws NoSuchAlgorithmException {
		while (!isValidPassword(password)) {
			
			System.out.println("Try again: ");
			password = sc.next();
		}
		this.password = password;
    }
    
    private boolean isValidPassword(String password1) {
    	if (password1 != null && password1.trim().length() >= MIN_LENGTH_OF_PASSWORD) {
			return true;
		}
		System.out.println("Password must be " + MIN_LENGTH_OF_PASSWORD + "or more than" + MIN_LENGTH_OF_PASSWORD + "characters!");
		return false;
	}

    
    private void setEmail(String email) {
		while (!isValidEmail(email) && magazin.getUsers().containsKey(email)) {
			if(!isValidEmail(email)) {
				System.out.println("Invlid email address.Try again: ");
			}else {
				System.out.println("This email exist, please enter other email!");
			}
			email = sc.next();
		}
		this.email = email;
    }
    
	private boolean isValidEmail(String email1) {
		Pattern regexPt = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
		Matcher regexMt = regexPt.matcher(email1);
		if (regexMt.matches()) {
			return true;
		}
		System.out.println("Not correct email adress!");
		return false;
	}
	
	public void actions(Vmzona magazin) throws Exception {
    	
		System.out.println("1 New User");
	    System.out.println("2.Exist User");
	    int choose = sc.nextInt();
	    
	    if(choose == 1) {
           this.CreateNewUser(magazin);
           choose = 2;
	    }   
        if(choose == 2) {
            this.logInLIkeExistUser(magazin);
        }
        
        magazin.izkaraiVsichkiNalichniStoki();
        while(true) {
        	
	        System.out.println();
	        System.out.println("izlez ot saita s cifra 3 ili prodalji s 8");
	        int komanda = sc.nextInt();
	        if(komanda == 3) {
	        	System.out.println("do skoro!");
	        	return;
	        }
	        
	    	System.out.println("Enter a number of stock, which you want to order: ");
	        int nomerNaStoka = sc.nextInt();
	        if (magazin.proveriZaStoka(nomerNaStoka)) {
	            try {
	                Stoka stoka = magazin.remoteStoka(nomerNaStoka);
	                stokiVKolichka.add(stoka);
	                magazin.addOrders(stoka);
	                this.sumOrder += stoka.getCena();
	                System.out.println();
	                
	                this.whatYouWant(magazin);
	                
	                System.out.println("Finished");
        	    	System.out.println("Continue");
        	    	String  st = sc.next();
        	    	if(st.equals("Finished")) {
        	    		this.forExit(magazin);
        	    		break;
        	    	}else {
        	    		continue;
        	    	}
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }else {
	        	System.out.println("This stock not exist!");
	        	continue;
	        }
        }
    }
	
	private void forExit(Vmzona magazin) {
		magazin.dostavi();
		System.out.println("Thank you for shopping with us.");
		System.out.println("Have a nice day! ;)");
		System.out.println();
	}
	
	private void whatYouWant(Vmzona magazin) {
    	System.out.println("1.Continue with shopping.");
    	System.out.println("2.Go to shopping cart!");
    	int choose = sc.nextInt();
    	switch(choose) {
	    	case 1:
	            System.out.println();
	            System.out.println("Enter a number of stock, which you want to order: ");
	            int nomerNaStoka = sc.nextInt();
	            try {
	                Stoka stoka = magazin.remoteStoka(nomerNaStoka);
	                this.stokiVKolichka.add(stoka);
	                magazin.addOrders(stoka);
	                this.sumOrder += stoka.getCena();
	                System.out.println();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            this.whatYouWant(magazin);
	            break;
	    	case 2:
	    		System.out.println("List of orders:");
	    		this.listAllOrders();
	    		break;
	    	default: 
	    		System.out.println("Incorrect number!");
	    }
    }
    
    private void logInLIkeExistUser(Vmzona magazin) {
    	
    	System.out.println("Enter email");
        String email1 = sc.next();
        System.out.println("Enter password");
        String password1 = sc.next();
        
        User user = magazin.getUsers().get(email1);
        while (user == null || !user.getPassword().equals(password1)) {
            System.out.println("Sorry, wrong email or password!");
            System.out.println("Enter email");
            email1 = sc.next();
            System.out.println("Enter password");
            password1 = sc.next();
            user = magazin.getUsers().get(email1);
        }
        
        System.out.println("Show my profile: ");
        System.out.println("Yes or No");
        String s = sc.next();
        if(s.equals("Yes")){
        	User user1 = magazin.getUsers().get(email1);
        	System.out.println(user1.getOwnProfile());
        	System.out.println();
        }
        
    }
    
    private void CreateNewUser(Vmzona magazin){
    	
    	System.out.println("Enter email");
        String email = sc.next();
        
        System.out.println("Enter password");
        String password = sc.next();
        
        magazin.dobaviUser(email, password);
	}
    
   
    public void listAllOrders() {
        for (Stoka st : this.stokiVKolichka) {
            System.out.println(st);
        }
        System.out.println("Your total sum is " + this.getSumOrder() + "$");
        System.out.println();
    }
  
    /*public static User daiMiUser(int number, String email, String password) throws Exception {
        switch (number) {
            case 2:
            	return new User();
            case 3:
                return new Admin(email, password);
        }
        throw new Exception("Bye!");
    }*/
    
    
    @Override
    public boolean equals(Object otherUser) {
        if (otherUser instanceof User)
            return this.email.equals(((User) otherUser).email);
        return false;
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @Override
    public String toString() {
        return "[email -> " + email + ", password -> " + password + "]";
    }

    public Profile getOwnProfile() {
		return ownProfile;
	}

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }
    
    public static int getSumOrder() {
		return sumOrder;
	}
}