import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Vmzona {
    private static final int BROI_RAZLICHNI_STOKI = 10;
    
	private String ime;
    private int oborot;
    private Map<Kategoriq, TreeMap<Integer, Stoka>> stoki = new HashMap<Kategoriq, TreeMap<Integer, Stoka>>();
    private Map<String, User> users = new HashMap<String, User>();
    private List<Dostavchik> dostavchici = new ArrayList<Dostavchik>(Arrays.asList(new Dostavchik("Mircho"), 
    																				new Dostavchik("Pesho"), 
    																				new Dostavchik("Lacho"),
    																				new Dostavchik("Kaloqn")));
    
    private List<Stoka> porachaniStoki = new ArrayList<Stoka>();

    public Vmzona(String ime) {
        this.ime = ime;
        this.oborot = 0;
    }

   
    public void dostavi(){
    	Dostavchik randomDostavchik = (Dostavchik) getRandom(this.dostavchici);
    	System.out.println("The provider " + randomDostavchik.getName() + " will receive your order within two days!");
	}
    
    private static Object getRandom(List list) {
		return list.get(new Random().nextInt(list.size()));
	}
 
    public void addStoka(Kategoriq kategoriq, Stoka stoka) {
        if (!stoki.containsKey(kategoriq)) {
            stoki.put(kategoriq, new TreeMap<>());
        }
        stoki.get(kategoriq).put(stoka.getNomerNaStoka(), stoka);
    }

    public Stoka remoteStoka(int nomerNaStoka) throws Exception {
        for (Map.Entry<Kategoriq, TreeMap<Integer, Stoka>> entry : stoki.entrySet()) {
            if (entry.getValue().containsKey(nomerNaStoka)) {
                Stoka stoka = entry.getValue().get(nomerNaStoka);
                entry.getValue().remove(nomerNaStoka);
                this.oborot += stoka.getCena();
                return stoka;
            }
        }
        throw new Exception("The stock not exist");
    }

    Scanner sc = new Scanner(System.in);
    
    public void izkaraiVsichkiNalichniStoki() {
        System.out.println("Choose criterion for sorting: ");
        System.out.println("1 - price");
        System.out.println("2 - name");
        System.out.println("3 - unique number of stock");
        int kriterij = sc.nextInt();

        for (Map.Entry<Kategoriq, TreeMap<Integer, Stoka>> entry : stoki.entrySet()) {
            System.out.println("kategoriq " + entry.getKey());
            System.out.println("------------------");
            switch (kriterij) {
                case 1:
                    entry.getValue().values().stream().sorted((stoka1, stoka2) -> {
                        if (stoka1.getCena() != stoka2.getCena()) {
                            return stoka1.getCena() - stoka2.getCena();
                        }
                        return stoka1.getIme().compareTo(stoka2.getIme());
                    }).forEach(stoka -> System.out.println(stoka));
                    System.out.println();
                    continue;
                case 2:
                    entry.getValue().values().stream().sorted((stoka1, stoka2) -> {
                        if (stoka1.getIme().compareTo(stoka2.getIme()) == 0) {
                            return stoka1.getCena() - stoka2.getCena();
                        }
                        return stoka1.getIme().compareTo(stoka2.getIme());
                    }).forEach(stoka -> System.out.println(stoka));
                    System.out.println();
                    continue;
                case 3:
                    entry.getValue().values().stream().forEach(stoka -> System.out.println(stoka));
                    System.out.println();
                    continue;
                default:
                    System.out.println("Incorrect number!");
                    entry.getValue().values().stream().forEach(stoka -> System.out.println(stoka));
            }
            System.out.println();
        }
    }
    
    public void printAllProviders() {
    	for(Dostavchik d : dostavchici) {
    		System.out.println(d);
    	}
    }
    
    public boolean proveriZaStoka(int nomer) {
        for (Map.Entry<Kategoriq, TreeMap<Integer, Stoka>> entry : stoki.entrySet()) {
            if (entry.getValue().containsKey(nomer)) {
                return true;
            }
        }
        return false;
    }
    
    public void addOrders(Stoka stoka) {
    	porachaniStoki.add(stoka);
     }
    
    public void dobaviUser(String email, String parola) {
        if (!users.containsKey(email)) {
            users.put(email, new User(email, parola));
        }
    }
    public void premahniUser(String email) {
        if (users.containsKey(email)) {
            users.remove(email);
        }
    }
    
    public void getOrderStocks() {
    	int counter = 1;
    	for (Stoka s : porachaniStoki) {
            System.out.println(counter + ". id: " + s.getNomerNaStoka() + " - " + s.getIme());
            counter++;
        }
    }
    
    public Map<String, User> getUsers() {
    	return Collections.unmodifiableMap(users);
    }
    
    
    public int getOborot() {
		return oborot;
	}
}