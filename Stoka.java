package vmzona;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Stoka implements Comparable<Stoka> {
    
	private class Sklad {
       
	   private List<String> avtochasti = new ArrayList<String>(Arrays.asList("djanti, akumulator", "far", "chstachki"));
        private List<String> parfyomi = new ArrayList<String>(Arrays.asList("refan", "paris", "playboy"));
        private List<String> belyo = new ArrayList<String>(Arrays.asList("slip", "prashki s dantela", "bokserki"));
        private List<String> halati = new ArrayList<String>(Arrays.asList("pamuchen halat", "koprinen halat", "halat s aplikacii"));
        private List<String> chanti = new ArrayList<String>(Arrays.asList("chanta ot istinska koja", "chanta ot izkustvena koja", "polirana chanta"));
        private List<String> obuvki = new ArrayList<String>(Arrays.asList("cherni botushi", "boti s visok tok", "rozovi kecove", "sandali", "ejednevni obuvki"));
        private List<String> chasovnici = new ArrayList<String>(Arrays.asList("roleks", "frenski vodoustoichiv chasovnik", "silikonov chasovnik"));
        private List<String> bijuta = new ArrayList<String>(Arrays.asList("grivna", "grivna sys syrce", "kulie s diamant", "prysten", "srebarni obeci"));
        private List<String> drehi = new ArrayList<String>(Arrays.asList("bluza s gol grab", "riza", "danki", "lqtna jiletka", "kojen klin", 
																		  "teniska s aplikaciq", "cheren potnik", "elegantna chervena roklq", 
																		  "ejednevna roklq", "kysa pola", "kojeno qke", "puhen elek ot zaek"));
        private List<String> podaryci = new ArrayList<String>(Arrays.asList("podaryk zaek", "podaryk dqdo mraz", "podarychna chasha"));
        private List<String> sport = new ArrayList<String>(Arrays.asList("topka za beizbol", "federbal", "masa za tenis", "bqgashta pateka"));
        private List<String> knigi = new ArrayList<String>(Arrays.asList("voina i mir", "java za vseki", "detska kniga s prikazki", "Harry Potter", "Peter Pan", 
																		  "Liboven roman", "Jivot v skalite", "Zamryznaloto kralstvo", "Little Pretty Liars"));


        private List<String> marki = new ArrayList<String>(Arrays.asList("hitachi", "adidas", "puma", "D&G", "kompas", "zara"));
        private List<String> cvetove = new ArrayList<String>(Arrays.asList("yellow", "blue", "red", "pink", "green", "black"));
		
		//Добавих още стоки и промених имената на някои от съществуващите

    }

    private Kategoriq kategoriq;
    private String ime;
    private int cena;
    private String marka;
    private String cvqt;
    private static Sklad sklad;
    private int nomerNaStoka;
    private static int broqchNomeraNaStoki = 0;

    public Stoka(String ime, int cena, String marka, String cvqt) {
        this.ime = ime;
        this.cena = cena;
        this.marka = marka;
        this.cvqt = cvqt;
        this.nomerNaStoka = ++broqchNomeraNaStoki;
        sklad = new Sklad();
    }

    public static Stoka daiStoka(Kategoriq k) throws Exception {
    	switch (k) {
            case AVTOCHASTI:
                return napraviStoka(sklad.avtochasti);
            case PARFYUMI:
                return napraviStoka(sklad.parfyomi);
            case BELYO:
                return napraviStoka(sklad.belyo);
            case HALATI:
                return napraviStoka(sklad.halati);
            case CNANTI:
                return napraviStoka(sklad.chanti);
            case OBUVKI:
                return napraviStoka(sklad.obuvki);
            case CHASOVNICI:
                return napraviStoka(sklad.chasovnici);
            case BIJUTA:
                return napraviStoka(sklad.bijuta);
            case DREHI:
                return napraviStoka(sklad.drehi);
            case PODARYCI:
                return napraviStoka(sklad.podaryci);
            case SPORT:
                return napraviStoka(sklad.sport);
            case KNIGI:
                return napraviStoka(sklad.knigi);
        }
        throw new Exception("podal si mi null");
    }

    
    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static int randomZaKolekcii(List<String> kolekciq) {
        return new Random().nextInt(kolekciq.size());
    }
    
    private static Stoka napraviStoka(List<String> imena) {
        return new Stoka(imena.get(randomZaKolekcii(imena)),
                random(10, 100), sklad.marki.get(randomZaKolekcii(sklad.marki)),
                sklad.cvetove.get(randomZaKolekcii(sklad.cvetove))) {

        };
    }

    public String getIme() {
        return ime;
    }

    public int getCena() {
        return cena;
    }

    public String getMarka() {
        return marka;
    }

    public String getCvqt() {
        return cvqt;
    }

    @Override
    public int compareTo(Stoka o) {
        return this.nomerNaStoka - o.nomerNaStoka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stoka)) return false;
        Stoka stoka = (Stoka) o;
        return cena == stoka.cena &&
                Objects.equals(ime, stoka.ime) &&
                Objects.equals(marka, stoka.marka) &&
                Objects.equals(cvqt, stoka.cvqt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, cena, marka, cvqt);
    }

    @Override
    public String toString() {
        return "id = " + nomerNaStoka + 
        		" ime = " + ime +
                ", cena = " + cena +
                ", marka = " + marka +
                ", cvqt = " + cvqt ;
    }

    public int getNomerNaStoka() {
        return nomerNaStoka;
    }
}
