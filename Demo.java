package vmzona;

import java.util.*;

public class Demo {


    public static final int BROI_RAZLICHNI_STOKI = 10;
    public static final int bROI_KATEGORII = Kategoriq.values().length;

    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static int randomZaKolekcii(List kolekciqSize) {
        return new Random().nextInt(kolekciqSize.size());
    }


    public static void main(String[] args) {
        try {

            Scanner sc = new Scanner(System.in);


            Vmzona magazin = new Vmzona("Vmzona");
            magazin.addStoka(Kategoriq.AVTOCHASTI, new Stoka("guma", 1, "iron", "lilav"));

            for (int i = 0; i < BROI_RAZLICHNI_STOKI; i++) {

                for (int j = 0; j < bROI_KATEGORII; j++) {
                    try {
                        magazin.addStoka(Kategoriq.values()[j], Stoka.daiStoka(j));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            System.out.println("MENU VmZona");
            System.out.println("--------");
            System.out.println("vyvedi 1 za : Guest");
            System.out.println("vyvedi 2 za : User");
            System.out.println("vyvedi 3 za : Administrator");
            System.out.println("vyvedi 0 za : Exit");


            int choose = sc.nextInt();

            while (true) {
                System.out.println("ako iskash da izlezesh natisni 0");
                if (choose == 0) {
                    System.out.println("do skoro , pozdravi ot VmZona");
                    break;
                }
                try {
                    User user = User.daiMiUser(choose, "", "");
                    user.buySomething();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

        }
    }
}








