package vmzona;


import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Sklad {
    private Vmzona magazine;
    private List<String> avtochasti = new ArrayList<String>(Arrays.asList("akumulator", "far", "chstachki"));
    private List<String> parfyomi = new ArrayList<String>(Arrays.asList("refan", "paris", "playboy"));
    private List<String> belyo = new ArrayList<String>(Arrays.asList("slip", "prashka", "bokserka"));
    private List<String> halati = new ArrayList<String>(Arrays.asList("golqm", "malyk", "sreden"));
    private List<String> chanti = new ArrayList<String>(Arrays.asList("kojena", "izkustvena", "polirana"));
    private List<String> obuvki = new ArrayList<String>(Arrays.asList("botush", "bota", "platnenka"));
    private List<String> chasovnici = new ArrayList<String>(Arrays.asList("roleks", "frenski vodoustoichiv", "chasovnik sys strelki"));
    private List<String> bijuta = new ArrayList<String>(Arrays.asList("grivna", "sindjir", "prysten"));
    private List<String> drehi = new ArrayList<String>(Arrays.asList("bluza", "riza", "ancug"));
    private List<String> podaryci = new ArrayList<String>(Arrays.asList("podaryk zaek", "podaryk dqdo mraz", "podarychna chasha"));
    private List<String> sport = new ArrayList<String>(Arrays.asList("topka", "federbal", "hilka"));
    private List<String> knigi = new ArrayList<String>(Arrays.asList("voina i mir", "java za wseki", "kniga s prikazki"));

    List<String> marki = new ArrayList<String>(Arrays.asList("hitachi", "adidas", "puma", "dolche", "kompas", "zara"));
    List<String> cvetove = new ArrayList<String>(Arrays.asList("jylt", "sin", "cherven", "rozov", "zalen", "cheren"));

    public Sklad(Vmzona magazine) {
        this.magazine = magazine;
    }

    private String getOtStringovaKolekciq(List<String> stokas) {
        return stokas.get(new Random().nextInt(stokas.size()));
    }

    public void zaredi() {

        Queue<Kategoriq> kategoriqs = new ArrayBlockingQueue<Kategoriq>(Kategoriq.values().length);
        int i = 0;
        while (i < Kategoriq.values().length) {
            ((ArrayBlockingQueue<Kategoriq>) kategoriqs).offer(Kategoriq.values()[i]);

            i++;
        }

        while (!kategoriqs.isEmpty()) {
            Kategoriq k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(avtochasti)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }

            k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(parfyomi)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }

            k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(belyo)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }
            k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(halati)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }
            k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(chanti)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }
            k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(obuvki)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }
            k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(chasovnici)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }
            k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(bijuta)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }
            k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(drehi)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }
            k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(podaryci)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }
            k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(sport)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }

            k = kategoriqs.poll();
            for (int j = 0; j < 20; j++) {
                magazine.addStoka(k, new Stoka((getOtStringovaKolekciq(knigi)), Demo.random(5, 100), getOtStringovaKolekciq(marki), getOtStringovaKolekciq(cvetove)));
            }
        }
    }
}

