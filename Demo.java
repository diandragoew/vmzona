package vmzona;

import java.util.*;

public class Demo {


    public static final int BROI_RAZLICHNI_STOKI = 100;
    public static final int bROI_KATEGORII = Kategoriq.values().length;

    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
    public static int randomZaKolekcii( List kolekciqSize) {
        return new Random().nextInt(kolekciqSize.size()) ;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Vmzona magazin = new Vmzona("Vmzona");
        magazin.addStoka(Kategoriq.AVTOCHASTI,new Stoka("guma",1,"iron","lilav"));

        for (int i = 0; i < BROI_RAZLICHNI_STOKI; i++) {

            for (int j = 0; j < bROI_KATEGORII; j++) {
                try {
                    magazin.addStoka(Kategoriq.values()[j], Stoka.daiStoka(j));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }




        Map<String, User> users = new HashMap<String, User>();
        users.put("gosho@abv.bg", new User("gosho@abv.bg", "marola"));


        System.out.println("MENU");
        System.out.println("--------");
        System.out.println("vyvedi 1 za : Guest");
        System.out.println("vyvedi 2 za : User");
        System.out.println("vyvedi 3 za : Administrator");
        System.out.println("vyvedi 4 za : Exit");

        int choose = sc.nextInt();
        if (choose == 1) {

            magazin.izkaraiVsichkiNalichniStoki();
        }
        if (choose == 2) {

            System.out.println("1.New User");
            System.out.println("2.Exist User");

            System.out.println("izbor");
            int choose1 = sc.nextInt();

            if (choose1 == 1) {

                String email = sc.nextLine();
                System.out.println("pas");
                String password = sc.nextLine();

                users.put(email, new User(email, password));
            } else {

                String email1 = sc.nextLine();
                System.out.println("pas");
                String password1 = sc.nextLine();

                User user = users.get(email1);
                if (user == null || !user.getPassword().equals(password1)) {

                    System.out.println("Sorry, wrong email or password!");
                } else {
                    magazin.izkaraiVsichkiNalichniStoki();
                }
            }
        }
        if (choose == 3) {
            String emailAdministrator = "strogoSecretno@abv.bg";
            String checkEmail = sc.nextLine();
            if (checkEmail.equals(emailAdministrator)) {
                //TODO...
            } else {
                System.out.println("You have not access to this information!");
            }
        }

        if (choose == 4) {

        }
    }


}

