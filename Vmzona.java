package vmzona;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Vmzona {
    private String ime;
    private Sklad sklad;
    private Map<Kategoriq, TreeSet<Stoka>> stoki = new HashMap<Kategoriq, TreeSet<Stoka>>();

    public Vmzona(String ime) {
        this.ime = ime;
        sklad = new Sklad(this);
    }

    public void zarediSe(){
        sklad.zaredi();
    }

    public void addStoka(Kategoriq kategoriq, Stoka stoka) {
        if (!stoki.containsKey(kategoriq)) {
            stoki.put(kategoriq, new TreeSet<>());
        }
        stoki.get(kategoriq).add(stoka);
    }

    public void remoteStoka(Kategoriq kategoriq, Stoka stoka) throws Exception {
        if (!stoki.containsKey(kategoriq)) {
            throw new Exception("nqma takwa stoka");
        }
        stoki.get(kategoriq).remove(stoka);
    }

    public void izkaraiVsichkiNalichniStoki() {
        for (Map.Entry<Kategoriq, TreeSet<Stoka>> entry : stoki.entrySet()) {
            System.out.println("kategoriq " + entry.getKey());
            for (Stoka stoka : entry.getValue()) {
                System.out.println(stoka);
            }
            System.out.println();
        }
    }
}
