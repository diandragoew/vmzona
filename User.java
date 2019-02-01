package vmzona;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class User {
	
    private String email;
    private String password;
    private Set<Stoka> stokiVKolichka = new TreeSet<>();
    private static int sumOrder = 0; 
    private static Vmzona magazin;
    
  //********************************************************************************************************* 
    public static class Admin extends User {
=======
import java.util.*;

public class User {
    Scanner sc = new Scanner(System.in);

    public static class Admin extends User {

        public Admin(String email, String password) {
            super("strogoSecretno@abv.bg", password);
        }

        public void buySomething() throws Exception {
            System.out.println("ti si administrator, vlez kato user za da pazaruwash");
            System.out.println("ili");
            System.out.println("vyvedi sekretniq imeil za prodyljavane kato administrator");
            String checkEmail = sc.nextLine();
            if (checkEmail.equals(getEmail())) {
                System.out.println("uspshen whod kato admin , moga da maham useri");
            } else {
                throw new Exception("You have not access to this information!");
            }
        }

        private void premahniUser() {
            for (Map.Entry<String, User> entry : magazin.getUsers().entrySet()) {
                System.out.println("imeil na usera  " + entry.getKey() + " danni na usera " + entry.getValue());
                System.out.println();
            }
            System.out.println("wywedi imeil ako iskash da premahnesh user ");
            String imeil = sc.nextLine();
            if (magazin.getUsers().containsKey(imeil)) {
                magazin.getUsers().remove(imeil);
                for (Map.Entry<String, User> entry : magazin.getUsers().entrySet()) {
                    System.out.println("imeil na usera  " + entry.getKey() + " danni na usera " + entry.getValue());
                    System.out.println();
                }
            } else {
                System.out.println("geshen meil adres izkarvam te ot saita avtomatichno");
                return;
            }
        }
    }


    public static class Guest extends User {
        public Guest() {
            super();
        }

        public void buySomething() throws Exception {
            getMagazin().izkaraiVsichkiNalichniStoki();
            System.out.println("ti si Guest, vlez kato user za da pazaruwash");
            super.buySomething();

        }
    }


    private String email;
    private String password;
    private Set<Stoka> stokiVKolichka = new TreeSet<>();
    private int pari;
    private static Vmzona magazin;
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127

        public Admin(String email, String password) {
            super("strogoSecretno@abv.bg", "naidobriqadministrator28");
        }
        
        private void message() {
        	System.out.println("If you want to shopping, sing in like User!");
            System.out.println("or");
            System.out.println("If you want to continue like administrator, enter name and password!");
        }

        public void doSomething() throws Exception {
        	Scanner sc = new Scanner(System.in);
        	
        	this.message();
        	String desire = sc.nextLine();
            if(desire.equals("USER")) {
            	
            	User user = new User("", "");
            	user.doSomething();
            	
            }if(desire.equals("ADMINISTRATOR")) {
            	
            	String checkEmail = sc.nextLine();
	            if (checkEmail.equals(getEmail())) {
	                System.out.println("Successful");
	                this.listOfRights();
	                int choose = sc.nextInt();
	                
	                switch(choose) {
	                	case 1:
	                		this.premahniUser();
	                		break;
	                	case 2:
	                		this.showOborot();//nedovarsheno
	                		break;
	                	case 3:
	                		this.printThreeWorstWorker(); //Nedovarsheno
	                		break;
	                	case 4:
	                		this.printStocks();
	                		break;
	                	default:
	                		System.out.println("Incorrect");
	                }
	            } else {
	                throw new Exception("You have not access to this information!");
	            }
            }
        }
        
        private void listOfRights() {
			System.out.println("1 - Remove user/s");
			System.out.println("2 - Check for turnover");
			System.out.println("3 - Show Three Worst Workers");
			System.out.println("4 - Show all stocks in store");
			System.out.println("5 - Exit");
		}

        private void printStocks() {
        	magazin.izkaraiVsichkiNalichniStoki();
        }

		private void printThreeWorstWorker() {
			magazin.threeWorstWorkers();
        }

		private void showOborot() {
			magazin.getOborot();
		}

		private void premahniUser() {
            for (Map.Entry<String, User> entry : magazin.getUsers().entrySet()) {
                System.out.println("email of user  " + entry.getKey() + " data of user " + entry.getValue());
                System.out.println();
            }
            System.out.println("Enter email if you want to remove user ");
            String email = sc.nextLine();
            if (magazin.getUsers().containsKey(email)) {
                magazin.getUsers().remove(email);
                for (Map.Entry<String, User> entry : magazin.getUsers().entrySet()) {
                    System.out.println("email of user  " + entry.getKey() + " data of user " + entry.getValue());
                    System.out.println();
                }
            } else {
                System.out.println("Wrong email address");
                return;
            }
        }
    }
  //*********************************************************************************************************************** 
    
    Scanner sc = new Scanner(System.in);
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        pari = 100;
        this.magazin = magazin;

    }
<<<<<<< HEAD
    
    public User(){}
    
    public void doSomething() throws Exception {
    	this.whoAreYou();
    	System.out.println("Enter a number of stock, which you want to order: ");
        int nomerNaStoka = sc.nextInt();
        if (this.magazin.proveriZaStoka(nomerNaStoka)) {
            try {
                Stoka stoka = this.magazin.remoteStoka(nomerNaStoka);
                stokiVKolichka.add(stoka);
                System.out.println();
                this.whatYouWant();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     }
    
    private void whoAreYou() {
    	System.out.println("1 New User");
	    System.out.println("2.Exist User");
	    int choose = sc.nextInt();
	    
	    if(choose == 1) {
            System.out.println("Enter email");
            String email = sc.nextLine();
            while (magazin.getUsers().containsKey(email)) {
                System.out.println("This email exist, please enter other email!");
                email = sc.nextLine();
            }
            System.out.println("Enter password");
            String password = sc.nextLine();
            magazin.getUsers().put(email, new User(email, password));
            choose = 2;
	    }   
        if(choose == 2) {
            System.out.println("Enter email");
            String email1 = sc.nextLine();
            System.out.println("Enter password");
            String password1 = sc.nextLine();
            
            User user = this.magazin.getUsers().get(email1);
            if (user == null || !user.getPassword().equals(password1)) {
                while (user == null || !user.getPassword().equals(password1)) {
                    System.out.println("Sorry, wrong email or password!");
                    System.out.println("Enter email");
                    email1 = sc.nextLine();
                    System.out.println("Enter password");
                    password1 = sc.nextLine();
                    user = this.magazin.getUsers().get(email1);
                }
            }else {
                //while(true) {
                    this.magazin.izkaraiVsichkiNalichniStoki();
                    System.out.println();
                //}
            }
=======

    public User() {

    }

    public static User daiMiUser(int nomer, String email, String password) throws Exception {
        switch (nomer) {
            case 1:
                return new Guest();
            case 2:
                return new User(email, password);
            case 3:
                return new Admin(email, password);
        }
        throw new Exception("do skoro , pozdravi ot vmzona");

    }

    public void buySomething() throws Exception {
        System.out.println("vyvedi  1 New User");
        System.out.println("vyvedi  2.Exist User");
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                System.out.println("vyvedi imeil");
                String email = sc.nextLine();
                System.out.println("vyvedi pas");
                String password = sc.nextLine();
                while (magazin.getUsers().containsKey(email)) {
                    System.out.println("syshtesvuwa takyw imeil weche wywedi drug");
                    email = sc.nextLine();
                }
                magazin.getUsers().put(email, new User(email, password));
            case 2:
                System.out.println("vyvedi imeil");
                String email1 = sc.nextLine();
                System.out.println("pas");
                String password1 = sc.nextLine();
                User user = magazin.getUsers().get(email1);
                if (user == null || !user.getPassword().equals(password1)) {
                    while (user == null || !user.getPassword().equals(password1)) {
                        System.out.println("Sorry, wrong email or password!");
                        System.out.println("vyvedi imeil");
                        email1 = sc.nextLine();
                        System.out.println("pas");
                        password1 = sc.nextLine();
                        user = magazin.getUsers().get(email1);
                    }
                } else {
                    while (true) {
                        magazin.izkaraiVsichkiNalichniStoki();
                        System.out.println();
                        System.out.println("izlez ot saita s cifra 3");
                        int komanda = sc.nextInt();
                        if (komanda == 3) {
                            System.out.println("do skoro , pozdravi ot VmZona");
                            return;
                        }
                        System.out.println("vyvedi nomer na stoka");
                        int nomerNaStoka = sc.nextInt();
                        if (magazin.proveriZaStoka(nomerNaStoka)) {
                            try {
                                Stoka stoka = magazin.remoteStoka(nomerNaStoka, this);
                                stokiVKolichka.add(stoka);
                                System.out.println();
                                System.out.println("eto ti gi zakupenite stoki");
                                listAllOrders();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127
        }
    }
    
    public void whatYouWant() {
    	int choose = sc.nextInt();
    	System.out.println("1.Continue with shopping.");
    	System.out.println("2.Go to shopping cart!");
    	switch(choose) {
	    	case 1:
	    		this.magazin.izkaraiVsichkiNalichniStoki();
	            System.out.println();
	            System.out.println("Enter a number of stock, which you want to order: ");
	            int nomerNaStoka = sc.nextInt();
	            try {
	                Stoka stoka = this.magazin.remoteStoka(nomerNaStoka);
	                stokiVKolichka.add(stoka);
	                System.out.println();
	                this.whatYouWant();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	    	case 2:
	    		System.out.println("List of orders:");
	    		this.listAllOrders();
	    }
    }
   
    public void listAllOrders() {
        System.out.println("izberi kriterii za sortirane");
        System.out.println("vyvedi  1 : cena");
        System.out.println("vyvedi  2 : ime");
        System.out.println("vyvedi  3 : nomer na stoka");
        int kriterij = sc.nextInt();
        switch (kriterij) {
            case 1:
                stokiVKolichka.stream().sorted((stoka1, stoka2) -> {
                    if (stoka1.getCena() != stoka2.getCena()) {
                        return stoka1.getCena() - stoka2.getCena();
                    }
                    return stoka1.getIme().compareTo(stoka2.getIme());
                }).forEach(stoka -> System.out.println(stoka));
                return;
            case 2:
                stokiVKolichka.stream().sorted((stoka1, stoka2) -> {
                    if (stoka1.getIme().compareTo(stoka2.getIme()) == 0) {
                        return stoka1.getCena() - stoka2.getCena();
                    }
                    return stoka1.getIme().compareTo(stoka2.getIme());
                }).forEach(stoka -> System.out.println(stoka));
                return;
            case 3:
                stokiVKolichka.stream().forEach(stoka -> System.out.println(stoka));
                return;
        }
<<<<<<< HEAD
        System.out.print(this.getSumOrder());
=======
        System.out.println("nepravilen  izbor na kriterii, sortirame vi gi po nomera ");
        stokiVKolichka.stream().forEach(stoka -> System.out.println(stoka));
        return;
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127
    }
    ///spored men nqma nujda da sa sortirani i v kolichkata, prosto trqbva spisak i obshtata suma nakraq
    
    
    public static User daiMiUser(int nomer, String email, String password) throws Exception {
        switch (nomer) {
            case 2:
                return new User(email, password);
            case 3:
                return new Admin(email, password);
        }
        throw new Exception("Bye!");
    }
    
    
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
        return "email = " + email + ", password = " + password;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }
<<<<<<< HEAD
    
    public static int getSumOrder() {
		return sumOrder;
	}
=======

    public Vmzona getMagazin() {
        return magazin;
    }

    public int getPari() {
        return pari;
    }
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127
}
