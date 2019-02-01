package vmzona;

<<<<<<< HEAD
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
=======
import java.util.*;
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127

public class Vmzona {
    private String ime;
    private int oborot;
    private Map<Kategoriq, TreeMap<Integer, Stoka>> stoki = new HashMap<Kategoriq, TreeMap<Integer, Stoka>>();
    private Map<String, User> users = new HashMap<String, User>();
<<<<<<< HEAD
    private List<Worker> workers = new ArrayList<Worker>();
    private List<Dostavchik> dostavchici = new ArrayList<Dostavchik>();
    private Map<Stoka, Integer> zakupeniStoki = new TreeMap<Stoka, Integer>();

    public Vmzona(String ime) {
        this.ime = ime;
        users.put("niki@abv.bg", new User("niki@abv.bg", "talent"));
=======

    public Vmzona(String ime) {
        this.ime = ime;
        users.put("gosho@abv.bg", new User("gosho@abv.bg", "marola"));

>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127
    }
   
  //******
    public void addWorker(Worker worker) {
		if (worker != null) {
			this.workers .add(worker);
		}
	}
    
    public void addDostavchik(Dostavchik dostavchik) {
		if (dostavchik != null) {
			this.dostavchici.add(dostavchik);
		}
	}

<<<<<<< HEAD
    public void dostavi(Map<Stoka, Integer> stokiZaDostavka){
    	Dostavchik randomDostavchik = (Dostavchik) getRandom(this.dostavchici);
    	Worker randomWorker = (Worker) getRandom(this.workers);
		this.decreaseMoney(randomDostavchik.zaredi(zakupeniStoki));
		randomWorker.otpishi(zakupeniStoki);
	}
    
    public void decreaseMoney(int money) {
		if (money > 0) {
			this.oborot -= money;
		}
	}
    
    private static Object getRandom(List list) {
		return list.get(new Random().nextInt(list.size()));
	}
    
    public void threeWorstWorkers() {
		Collections.sort(this.workers, (w1, w2) -> w1.getOtpisaniStoki() - w2.getOtpisaniStoki());
		System.out.println(this.workers.get(0));
		System.out.println(this.workers.get(1));
		System.out.println(this.workers.get(2));
	}
  //*****
    
	public void addStoka(Kategoriq kategoriq, Stoka stoka) {
=======
    Scanner sc = new Scanner(System.in);

    public void addStoka(Kategoriq kategoriq, Stoka stoka) {
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127
        if (!stoki.containsKey(kategoriq)) {
            stoki.put(kategoriq, new TreeMap<>());
        }
        stoki.get(kategoriq).put(stoka.getNomerNaStoka(), stoka);
    }

<<<<<<< HEAD
    public Stoka remoteStoka(int nomerNaStoka) throws Exception {
=======
    public Stoka remoteStoka(int nomerNaStoka, User user) throws Exception {
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127
        for (Map.Entry<Kategoriq, TreeMap<Integer, Stoka>> entry : stoki.entrySet()) {
            if (entry.getValue().containsKey(nomerNaStoka)) {
                Stoka stoka = entry.getValue().get(nomerNaStoka);
                if (stoka.getCena() <= user.getPari()) {
                    entry.getValue().remove(nomerNaStoka);
                    oborot += stoka.getCena();
                    return stoka;
                } else {
                    throw new Exception("klienta " + user + " nqma dostatychno pari");

                }
            }
        }
        throw new Exception("The stock not exist");
    }
    ///Klienta nqma ogranicheniq s parite, toi si pazaruva i plashta posle na mqsto sled dostavka!

    Scanner sc = new Scanner(System.in);
    
    public void izkaraiVsichkiNalichniStoki() {
        System.out.println("izberi kriterii za sortirane");
<<<<<<< HEAD
        System.out.println("1 - price");
        System.out.println("2 - name");
        System.out.println("3 - unique number of stock");
=======
        System.out.println("vyvedi  1 : cena");
        System.out.println("vyvedi  2 : ime");
        System.out.println("vyvedi  3 : nomer na stoka");
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127
        int kriterij = sc.nextInt();

        for (Map.Entry<Kategoriq, TreeMap<Integer, Stoka>> entry : stoki.entrySet()) {
            System.out.println("kategoriq " + entry.getKey());
<<<<<<< HEAD
            System.out.println("------------------");
=======
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127
            switch (kriterij) {
                case 1:
                    entry.getValue().values().stream().sorted((stoka1, stoka2) -> {
                        if (stoka1.getCena() != stoka2.getCena()) {
                            return stoka1.getCena() - stoka2.getCena();
                        }
                        return stoka1.getIme().compareTo(stoka2.getIme());
                    }).forEach(stoka -> System.out.println(stoka));
<<<<<<< HEAD
                    System.out.println();
=======
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127
                    continue;
                case 2:
                    entry.getValue().values().stream().sorted((stoka1, stoka2) -> {
                        if (stoka1.getIme().compareTo(stoka2.getIme()) == 0) {
                            return stoka1.getCena() - stoka2.getCena();
                        }
                        return stoka1.getIme().compareTo(stoka2.getIme());
                    }).forEach(stoka -> System.out.println(stoka));
<<<<<<< HEAD
                    System.out.println();
                    continue;
                case 3:
                    entry.getValue().values().stream().forEach(stoka -> System.out.println(stoka));
                    System.out.println();
                    continue;
                default:
                    System.out.println("Incorrect number!");
=======
                    continue;
                case 3:
                    entry.getValue().values().stream().forEach(stoka -> System.out.println(stoka));
                    continue;
                default:
                    System.out.println("nepravilen  izbor na kriterii, sortirame vi gi po nomera ");
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127
                    entry.getValue().values().stream().forEach(stoka -> System.out.println(stoka));
            }
            System.out.println();
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
<<<<<<< HEAD
    
    public Map<String, User> getUsers() {
    	return Collections.unmodifiableMap(users);
    }
    
    public int getOborot() {
		return oborot;
	}
=======

    public Map<String, User> getUsers() {
        return Collections.unmodifiableMap(users);
    }
>>>>>>> db5efaa7ff83a3827687f17188a493d11c8ce127
}
