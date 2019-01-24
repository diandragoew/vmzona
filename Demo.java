package vmzona;

import java.util.Random;

public class Demo {

    public static final int BROI_ARTIKULI = 100;

    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }


    public static void main(String[] args) {


        Vmzona magazin = new Vmzona("Vmzona");
        magazin.zarediSe();
        magazin.izkaraiVsichkiNalichniStoki();
    }
}
