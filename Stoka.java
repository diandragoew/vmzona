package vmzona;

import java.util.Objects;

public class Stoka implements Comparable<Stoka>{
    private String ime;
    private int cena;
    private String marka;
    private String cvqt;

    public Stoka(String ime, int cena, String marka, String cvqt) {
        this.ime = ime;
        this.cena = cena;
        this.marka = marka;
        this.cvqt = cvqt;
    }

    @Override
    public int compareTo(Stoka o) {
        if(this.ime.compareTo(o.ime)!=0){
            return this.ime.compareTo(o.ime);
        }else{
            if (this.marka.compareTo(o.marka)!=0){
                return this.marka.compareTo(o.marka);
            }else {
              return   this.cena-o.cena;
            }

        }
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
        return "ime='" + ime + '\'' +
                ", cena=" + cena +
                ", marka='" + marka + '\'' +
                ", cvqt='" + cvqt + '\'' +
                '}';
    }
}
