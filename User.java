package vmzona;

import java.util.*;

public class User {
    Scanner sc = new Scanner(System.in);

    public static class Admin extends User {

        public Admin(String email, String password) {
            super("strogoSecretno@abv.bg", password);
        }

        public void buySomething() throws Exception {
            System.out.println("ti si administrator, vlez kato user za da pazaruwash");
            System.out.println("ili");
            System.out.println("vyvedi sekretniq imeil za prodyljavane kato administrator");
            String checkEmail = sc.nextLine();
            if (checkEmail.equals(getEmail())) {
                System.out.println("uspshen whod kato admin , moga da maham useri");
            } else {
                throw new Exception("You have not access to this information!");
            }
        }

        private void premahniUser() {
            for (Map.Entry<String, User> entry : magazin.getUsers().entrySet()) {
                System.out.println("imeil na usera  " + entry.getKey() + " danni na usera " + entry.getValue());
                System.out.println();
            }
            System.out.println("wywedi imeil ako iskash da premahnesh user ");
            String imeil = sc.nextLine();
            if (magazin.getUsers().containsKey(imeil)) {
                magazin.getUsers().remove(imeil);
                for (Map.Entry<String, User> entry : magazin.getUsers().entrySet()) {
                    System.out.println("imeil na usera  " + entry.getKey() + " danni na usera " + entry.getValue());
                    System.out.println();
                }
            } else {
                System.out.println("geshen meil adres izkarvam te ot saita avtomatichno");
                return;
            }
        }
    }


    public static class Guest extends User {
        public Guest() {
            super();
        }

        public void buySomething() throws Exception {
            getMagazin().izkaraiVsichkiNalichniStoki();
            System.out.println("ti si Guest, vlez kato user za da pazaruwash");
            super.buySomething();

        }
    }


    private String email;
    private String password;
    private Set<Stoka> stokiVKolichka = new TreeSet<>();
    private int pari;
    private static Vmzona magazin;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        pari = 100;
        this.magazin = magazin;

    }

    public User() {

    }

    public static User daiMiUser(int nomer, String email, String password) throws Exception {
        switch (nomer) {
            case 1:
                return new Guest();
            case 2:
                return new User(email, password);
            case 3:
                return new Admin(email, password);
        }
        throw new Exception("do skoro , pozdravi ot vmzona");

    }

    public void buySomething() throws Exception {
        System.out.println("vyvedi  1 New User");
        System.out.println("vyvedi  2.Exist User");
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                System.out.println("vyvedi imeil");
                String email = sc.nextLine();
                System.out.println("vyvedi pas");
                String password = sc.nextLine();
                while (magazin.getUsers().containsKey(email)) {
                    System.out.println("syshtesvuwa takyw imeil weche wywedi drug");
                    email = sc.nextLine();
                }
                magazin.getUsers().put(email, new User(email, password));
            case 2:
                System.out.println("vyvedi imeil");
                String email1 = sc.nextLine();
                System.out.println("pas");
                String password1 = sc.nextLine();
                User user = magazin.getUsers().get(email1);
                if (user == null || !user.getPassword().equals(password1)) {
                    while (user == null || !user.getPassword().equals(password1)) {
                        System.out.println("Sorry, wrong email or password!");
                        System.out.println("vyvedi imeil");
                        email1 = sc.nextLine();
                        System.out.println("pas");
                        password1 = sc.nextLine();
                        user = magazin.getUsers().get(email1);
                    }
                } else {
                    while (true) {
                        magazin.izkaraiVsichkiNalichniStoki();
                        System.out.println();
                        System.out.println("izlez ot saita s cifra 3");
                        int komanda = sc.nextInt();
                        if (komanda == 3) {
                            System.out.println("do skoro , pozdravi ot VmZona");
                            return;
                        }
                        System.out.println("vyvedi nomer na stoka");
                        int nomerNaStoka = sc.nextInt();
                        if (magazin.proveriZaStoka(nomerNaStoka)) {
                            try {
                                Stoka stoka = magazin.remoteStoka(nomerNaStoka, this);
                                stokiVKolichka.add(stoka);
                                System.out.println();
                                System.out.println("eto ti gi zakupenite stoki");
                                listAllOrders();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        }
    }

    public void listAllOrders() {
        System.out.println("izberi kriterii za sortirane");
        System.out.println("vyvedi  1 : cena");
        System.out.println("vyvedi  2 : ime");
        System.out.println("vyvedi  3 : nomer na stoka");
        int kriterij = sc.nextInt();
        switch (kriterij) {
            case 1:
                stokiVKolichka.stream().sorted((stoka1, stoka2) -> {
                    if (stoka1.getCena() != stoka2.getCena()) {
                        return stoka1.getCena() - stoka2.getCena();
                    }
                    return stoka1.getIme().compareTo(stoka2.getIme());
                }).forEach(stoka -> System.out.println(stoka));
                return;
            case 2:
                stokiVKolichka.stream().sorted((stoka1, stoka2) -> {
                    if (stoka1.getIme().compareTo(stoka2.getIme()) == 0) {
                        return stoka1.getCena() - stoka2.getCena();
                    }
                    return stoka1.getIme().compareTo(stoka2.getIme());
                }).forEach(stoka -> System.out.println(stoka));
                return;
            case 3:
                stokiVKolichka.stream().forEach(stoka -> System.out.println(stoka));
                return;
        }
        System.out.println("nepravilen  izbor na kriterii, sortirame vi gi po nomera ");
        stokiVKolichka.stream().forEach(stoka -> System.out.println(stoka));
        return;
    }

    @Override
    public boolean equals(Object otherUser) {
        if (otherUser instanceof User)
            return this.email.equals(((User) otherUser).email);
        return false;
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @Override
    public String toString() {
        return "email=" + email + ", password=" + password;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

    public Vmzona getMagazin() {
        return magazin;
    }

    public int getPari() {
        return pari;
    }
}
