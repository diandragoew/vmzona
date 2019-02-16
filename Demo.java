package vmzona;

import java.util.Scanner;

public class Demo {

    public static final int BROI_RAZLICHNI_STOKI = 10;


    public static void messageForExit(Vmzona magazin) {
        magazin.dostavi();
        System.out.println("Thank you for shopping with us.");
        System.out.println("Have a nice day! ;)");
        System.out.println();

        System.out.println("Enter 0 for exit or number from MENU(1- Guest,2- User,3- Administrator) for continue");
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {

            Vmzona magazin = new Vmzona("Vmzona");
            magazin.addStoka(Kategoriq.AVTOCHASTI, new Stoka("komplekt(2 gumi)", 25, "iron", "black"));


            for (Kategoriq k : Kategoriq.values()) {
                for (int i = 1; i <= BROI_RAZLICHNI_STOKI; i++) {
                    try {
                        magazin.addStoka(k, Stoka.daiStoka(k));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            System.out.println("MENU VmZona");
            System.out.println("--------");
            System.out.println("1 - Guest");
            System.out.println("2 - User");
            System.out.println("3 - Administrator");
            System.out.println("0 - Exit");

            String choose = sc.next();
            while (true) {
                if (choose.equalsIgnoreCase("0")) {
                    try {
                        Vmzona.messageForVote();
                    } catch (RatingException e) {
                        System.err.println((e.getMessage()));
                    }
                    return;
                } else {
                    if (choose.equalsIgnoreCase("1")) {

                        System.out.println("Guest!");

                        Guest guest = Guest.daiGuest();
                        magazin.izkaraiVsichkiNalichniStoki();

                        guest.action(magazin);

                        System.out.println("Enter \"Finished\" for Finished");
                        System.out.println("Enter \"Continue\" for Continue");
                        String st = sc.next();
                        if (st.equalsIgnoreCase("Finished")) {
                            messageForExit(magazin);
                            choose = sc.next();
                        } else {
                            continue;
                        }
                    }
                    if (choose.equalsIgnoreCase("2")) {
                        System.out.println("Other!!");
                        try {
                            User.actions(magazin);

                            System.out.println("Enter 0 for exit or number from MENU(1- Guest,2- User,3- Administrator) for continue ");
                            choose = sc.next();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (choose.equalsIgnoreCase("3")) {
                        Admin admin = new Admin(magazin);
                        try {
                            admin.actions();
                        } catch (AdminException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Enter 0 for exit or number from MENU(1- Guest,2- User,3- Administrator) for continue ");
                        choose = sc.next();
                    }
                    if (choose.charAt(0) < '0' || choose.charAt(0) > '3') {
                        System.out.println("Invalid number - you enter as a guest");
                        choose = "1";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

