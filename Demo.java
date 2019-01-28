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


        Map<String, User> users = new HashMap<String, User>();
        users.put("gosho@abv.bg", new User("gosho@abv.bg", "marola"));


        System.out.println("MENU VmZona");
        System.out.println("--------");
        System.out.println("vyvedi 1 za : Guest");
        System.out.println("vyvedi 2 za : User");
        System.out.println("vyvedi 3 za : Administrator");
        System.out.println("vyvedi 4 za : Exit");



        int choose = sc.nextInt();

        while (choose != 0) {
            System.out.println("ako iskash da izlezesh natisni 0");
            if (choose == 1) {

                magazin.izkaraiVsichkiNalichniStoki();
                System.out.println();
                System.out.println("izberi si stoka wywedi s  nomer na stokata");
                choose = sc.nextInt();
                System.out.println("vlez ili se registrirai");
                choose = 2;
            }
            if (choose == 2) {

                System.out.println("vyvedi  1 New User");
                System.out.println("vyvedi  2.Exist User");

                System.out.println("izbor");
                int choose1 = sc.nextInt();

                if (choose1 == 1) {
                    System.out.println("vyvedi imeil");
                    String email = sc.nextLine();
                    System.out.println("vyvedi pas");
                    String password = sc.nextLine();
                    while (users.containsKey(email)) {
                        System.out.println("syshtesvuwa takyw imeil weche wywedi drug");
                        email = sc.nextLine();
                    }
                    users.put(email, new User(email, password));
                    choose1=2;
                }
                if(choose1==2){
                    System.out.println("vyvedi imeil");
                    String email1 = sc.nextLine();
                    System.out.println("pas");
                    String password1 = sc.nextLine();

                    User user = users.get(email1);
                    if (user == null || !user.getPassword().equals(password1)) {

                        System.out.println("Sorry, wrong email or password!");
                    } else {
                        int komanda=0;
                        while(komanda!=3) {
                            magazin.izkaraiVsichkiNalichniStoki();
                            System.out.println();
                            System.out.println("natisni 1 za da dobavish stoka w kolichkata si i sled towa vyvedi nomera na izbranata stoka");
                            System.out.println("izlez ot saita s cifra 3");
                            komanda=sc.nextInt();
                            if(komanda==1) {
                                System.out.println("vyvedi nomer na stoka");
                                int nomerNaStoka = sc.nextInt();
                                if (magazin.proveriZaStoka(nomerNaStoka)) {
                                    try {
                                        user.buySomething(magazin.remoteStoka(nomerNaStoka));
                                        System.out.println();
                                        System.out.println("eto ti gi stokite zakupenite stoki");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        System.out.println("do skoro , pozdravi ot VmZona");
                    }
                }
            } else {
                if (choose == 3) {
                    String emailAdministrator = "strogoSecretno@abv.bg";
                    String checkEmail = sc.nextLine();
                    if (checkEmail.equals(emailAdministrator)) {
                        System.out.println("moga da maham useri");

                        for (Map.Entry<String, User> entry : users.entrySet()) {
                            System.out.println("imeil na usera  " + entry.getKey() + " danni na usera " + entry.getValue());
                            System.out.println();
                        }
                        System.out.println("wywedi imeil ako iskash da premahnesh user ");
                        String imeil = sc.nextLine();
                        if (users.containsKey(imeil)) {
                            users.remove(imeil);
                            for (Map.Entry<String, User> entry : users.entrySet()) {
                                System.out.println("imeil na usera  " + entry.getKey() + " danni na usera " + entry.getValue());
                                System.out.println();
                            }
                        } else {
                            System.out.println("geshen meil adres izkarvam te ot saita avtomatichno");
                            break;
                        }
                    }
                }  else {
                        System.out.println("You have not access to this information!");
                    }
                }

                if (choose == 4) {
                    System.out.println("do skoro , pozdravi ot VmZona");
                    break;
                }
            }
        }
    }


