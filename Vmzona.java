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

public class Vmzona {
    private String ime;
    private int oborot;
    private Map<Kategoriq, TreeMap<Integer, Stoka>> stoki = new HashMap<Kategoriq, TreeMap<Integer, Stoka>>();
    private Map<String, User> users = new HashMap<String, User>();
    private List<Worker> workers = new ArrayList<Worker>();
    private List<Dostavchik> dostavchici = new ArrayList<Dostavchik>();
    private Map<Stoka, Integer> zakupeniStoki = new TreeMap<Stoka, Integer>();

    public Vmzona(String ime) {
        this.ime = ime;
        users.put("niki@abv.bg", new User("niki@abv.bg", "talent"));
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
                oborot += stoka.getCena();
                return stoka;
            }
        }
        throw new Exception("The stock not exist");
    }
    ///Klienta nqma ogranicheniq s parite, toi si pazaruva i plashta posle na mqsto sled dostavka!

    Scanner sc = new Scanner(System.in);
    
    public void izkaraiVsichkiNalichniStoki() {
        System.out.println("izberi kriterii za sortirane");
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

    public boolean proveriZaStoka(int nomer) {
        for (Map.Entry<Kategoriq, TreeMap<Integer, Stoka>> entry : stoki.entrySet()) {
            if (entry.getValue().containsKey(nomer)) {
                return true;
            }
        }
        return false;
    }
    
    public Map<String, User> getUsers() {
    	return Collections.unmodifiableMap(users);
    }
    
    public int getOborot() {
		return oborot;
	}
}

