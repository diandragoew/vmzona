package vmzona;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Stoka implements Comparable<Stoka> {
    private class Sklad {
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


        private List<String> marki = new ArrayList<String>(Arrays.asList("hitachi", "adidas", "puma", "dolche", "kompas", "zara"));
        private List<String> cvetove = new ArrayList<String>(Arrays.asList("jylt", "sin", "cherven", "rozov", "zalen", "cheren"));


    }

    private Kategoriq kategoriq;
    private String ime;
    private int cena;
    private String marka;
    private String cvqt;
    private static Sklad sklad;
    private int nomerNaStoka;
    private static int broqchNomeraNaStoki=0;

    public Stoka( String ime, int cena, String marka, String cvqt) {
        this.ime = ime;
        this.cena = cena;
        this.marka = marka;
        this.cvqt = cvqt;
        this.nomerNaStoka=++broqchNomeraNaStoki;
        sklad = new Sklad();
    }

    public static Stoka daiStoka(int nomerKategoriq) throws Exception {
        switch (Kategoriq.values()[nomerKategoriq]) {
            case AVTOCHASTI:
                return napraviStoka(sklad.avtochasti);
            case PARFYOMI:
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

    private static Stoka napraviStoka(List<String> kategoriq) {
        return new Stoka(kategoriq.get(Demo.randomZaKolekcii(kategoriq)),
                Demo.random(10, 100), sklad.marki.get(Demo.randomZaKolekcii(sklad.marki)),
                sklad.cvetove.get(Demo.randomZaKolekcii(sklad.cvetove))) {

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
        return this.nomerNaStoka-o.nomerNaStoka;
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
        return   " nomerNaStoka=" + nomerNaStoka +
                " ime= '" + ime + '\'' +
                ", cena= " + cena +
                ", marka= '" + marka + '\'' +
                ", cvqt= " + cvqt ;
    }

    public int getNomerNaStoka() {
        return nomerNaStoka;
    }
}
