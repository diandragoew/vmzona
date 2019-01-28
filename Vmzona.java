package vmzona;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Vmzona {
    private String ime;
    private int oborot;
    private Map<Kategoriq, HashMap<Integer, Stoka>> stoki = new HashMap<Kategoriq, HashMap<Integer, Stoka>>();

    public Vmzona(String ime) {
        this.ime = ime;
    }


    public void addStoka(Kategoriq kategoriq, Stoka stoka) {
        if (!stoki.containsKey(kategoriq)) {
            stoki.put(kategoriq, new HashMap<>());
        }
        stoki.get(kategoriq).put(stoka.getNomerNaStoka(),stoka);
    }

    public Stoka remoteStoka( int nomerNaStoka) throws Exception {
        for (Map.Entry<Kategoriq, HashMap<Integer, Stoka>> entry : stoki.entrySet()) {
            if (entry.getValue().containsKey(nomerNaStoka)) {
                Stoka stoka = entry.getValue().get(nomerNaStoka);
                entry.getValue().remove(nomerNaStoka);
                oborot += stoka.getCena();
                return stoka;
            }
        }
        throw new Exception("nqma takwa stoka");
    }

    public void izkaraiVsichkiNalichniStoki() {
        for (Map.Entry<Kategoriq, HashMap<Integer, Stoka>> entry : stoki.entrySet()) {
            System.out.println("kategoriq " + entry.getKey());
            for (Map.Entry<Kategoriq, HashMap<Integer, Stoka>> e : stoki.entrySet()) {
                System.out.println(e.getValue());
            }
            System.out.println();
        }
    }

    public boolean proveriZaStoka(int nomer) {
        for (Map.Entry<Kategoriq, HashMap<Integer, Stoka>> entry : stoki.entrySet()) {
            if (entry.getValue().containsKey(nomer)) {
                return true;
            }
        }
        return false;
    }
}
