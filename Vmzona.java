package vmzona;

import java.util.*;

public class Vmzona {
    private String ime;
    private int oborot;
    private Map<Kategoriq, TreeMap<Integer, Stoka>> stoki = new HashMap<Kategoriq, TreeMap<Integer, Stoka>>();
    private Map<String, User> users = new HashMap<String, User>();

    public Vmzona(String ime) {
        this.ime = ime;
        users.put("gosho@abv.bg", new User("gosho@abv.bg", "marola"));

    }

    Scanner sc = new Scanner(System.in);

    public void addStoka(Kategoriq kategoriq, Stoka stoka) {
        if (!stoki.containsKey(kategoriq)) {
            stoki.put(kategoriq, new TreeMap<>());
        }
        stoki.get(kategoriq).put(stoka.getNomerNaStoka(), stoka);
    }

    public Stoka remoteStoka(int nomerNaStoka, User user) throws Exception {
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
        throw new Exception("nqma takwa stoka");
    }

    public void izkaraiVsichkiNalichniStoki() {
        System.out.println("izberi kriterii za sortirane");
        System.out.println("vyvedi  1 : cena");
        System.out.println("vyvedi  2 : ime");
        System.out.println("vyvedi  3 : nomer na stoka");
        int kriterij = sc.nextInt();

        for (Map.Entry<Kategoriq, TreeMap<Integer, Stoka>> entry : stoki.entrySet()) {
            System.out.println("kategoriq " + entry.getKey());
            switch (kriterij) {
                case 1:
                    entry.getValue().values().stream().sorted((stoka1, stoka2) -> {
                        if (stoka1.getCena() != stoka2.getCena()) {
                            return stoka1.getCena() - stoka2.getCena();
                        }
                        return stoka1.getIme().compareTo(stoka2.getIme());
                    }).forEach(stoka -> System.out.println(stoka));
                    continue;
                case 2:
                    entry.getValue().values().stream().sorted((stoka1, stoka2) -> {
                        if (stoka1.getIme().compareTo(stoka2.getIme()) == 0) {
                            return stoka1.getCena() - stoka2.getCena();
                        }
                        return stoka1.getIme().compareTo(stoka2.getIme());
                    }).forEach(stoka -> System.out.println(stoka));
                    continue;
                case 3:
                    entry.getValue().values().stream().forEach(stoka -> System.out.println(stoka));
                    continue;
                default:
                    System.out.println("nepravilen  izbor na kriterii, sortirame vi gi po nomera ");
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
}
