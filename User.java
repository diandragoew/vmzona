package vmzona;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class User {

    private static final int MIN_LENGTH_OF_PASSWORD = 6;
    private static final Scanner sc = new Scanner(System.in);


    private String email;
    private String password;
    private Set<Stoka> stokiVKolichka = new TreeSet<>();
    private int sumOrder;

    private Profile ownProfile;

    private static Vmzona magazin;


    public User(String email, String password, Vmzona mag) {
        this.setEmail(email, mag);
        this.setPassword(password);
        magazin = mag;
        this.ownProfile = new Profile();
    }

    private void setPassword(String password) {
        while (!isValidPassword(password)) {

            System.out.println("Try again: ");
            password = sc.next();
        }
        this.password = password;
    }

    private boolean isValidPassword(String password1) {
        if (password1 != null && password1.trim().length() >= MIN_LENGTH_OF_PASSWORD) {
            return true;
        }
        System.out.println("Password must be " + MIN_LENGTH_OF_PASSWORD + "or more than " + MIN_LENGTH_OF_PASSWORD + " characters!");
        return false;
    }


    private void setEmail(String email, Vmzona mag) {
        while (!isValidEmail(email) || mag.getUsers().containsKey(email)) {
            if (!isValidEmail(email)) {
                System.out.println("Invlid email address.Try again: ");
            } else {
                System.out.println("This email exist, please enter other email!");
            }
            email = sc.next();
        }
        this.email = email;
    }

    private boolean isValidEmail(String email1) {
        Pattern regexPt = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regexMt = regexPt.matcher(email1);
        if (regexMt.matches()) {
            return true;
        }
        System.out.println("Not correct email adress!");
        return false;
    }

    public static void actions(Vmzona magazin) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 New User");
        System.out.println("2.Exist User");
        String choose = sc.next();

        while ((!choose.equalsIgnoreCase( "1")) && (!choose.equalsIgnoreCase( "2"))) {
            System.out.println("1 New User");
            System.out.println("2.Exist User");
            choose = sc.next();
        }
        if (choose.equalsIgnoreCase( "1")) {
            createNewUser(magazin);
        }
        User currentUser = logInLIkeExistUser(magazin);
        actionsWhenYouLogged(currentUser);
    }

    private static void actionsWhenYouLogged(User user) {
        magazin.izkaraiVsichkiNalichniStoki();
        while (true) {

            System.out.println();
            System.out.println("Exit from site with 3 or continue with some other number.");
            String komanda = sc.next();
            if (komanda.equalsIgnoreCase("3")) {
                System.out.println("do skoro!");
                return;
            }

            System.out.println("Enter a number of stock, which you want to order: ");
            String nomerNaStoka = sc.next();
            if (magazin.proveriZaStoka(nomerNaStoka)) {
                try {
                    Stoka stoka = magazin.remoteStoka(Integer.parseInt(nomerNaStoka));
                    user.stokiVKolichka.add(stoka);
                    magazin.addOrders(stoka);
                    user.sumOrder += stoka.getCena();
                    System.out.println();

                    user.whatYouWant(magazin);

                    System.out.println("Enter \"Finished\" for Finished");
                    System.out.println("Enter \"Continue\" for Continue");
                    String st = sc.next();
                    if (st.equalsIgnoreCase("Finished")) {
                        user.forExit(magazin);
                        break;
                    } else {
                        continue;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("This stock not exist!");
                continue;
            }
        }
    }

    private void forExit(Vmzona magazin) {
        magazin.dostavi();
        System.out.println("Thank you for shopping with us.");
        System.out.println("Have a nice day! ;)");
        System.out.println();
    }

    private void whatYouWant(Vmzona magazin) {
        System.out.println("1.Continue with shopping.");
        System.out.println("2.Go to shopping cart!");
        String choose = sc.next();
        switch (choose) {
            case "1":
                System.out.println();
                System.out.println("Enter a number of stock, which you want to order: ");
                int nomerNaStoka = sc.nextInt();
                try {
                    Stoka stoka = magazin.remoteStoka(nomerNaStoka);
                    this.stokiVKolichka.add(stoka);
                    magazin.addOrders(stoka);
                    this.sumOrder += stoka.getCena();
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.whatYouWant(magazin);
                break;
            case "2":
                System.out.println("List of orders:");
                this.listAllOrders();
                break;
            default:
                System.out.println("Incorrect number!");
        }
    }

    private static User logInLIkeExistUser(Vmzona magazin) {
        System.out.println("LOGIN PAGE");
        System.out.println("Enter email");
        String email1 = sc.next();
        System.out.println("Enter password");
        String password1 = sc.next();

        User user = magazin.getUsers().get(email1);
        while (user == null || !user.getPassword().equals(password1)) {
            System.out.println("Sorry, wrong email or password!");
            System.out.println("if you do not have a profile, press 0, or press another key to continue.");
            String klavish = sc.next();
            if (klavish.equalsIgnoreCase("0")) {
                createNewUser(magazin);
            } else {
                System.out.println("Enter email");
                email1 = sc.next();
                System.out.println("Enter password");
                password1 = sc.next();
                user = magazin.getUsers().get(email1);
            }
        }
        System.out.println("Show my profile: ");
        System.out.println("Yes or No");
        String s = sc.next();
        if (s.equalsIgnoreCase("Yes")) {
            User user1 = magazin.getUsers().get(email1);
            System.out.println(user1.getOwnProfile());
            System.out.println();
        }
        return user;
    }

    private static void createNewUser(Vmzona magazin) {
        System.out.println("REGISTER PAGE");
        System.out.println("Enter email");
        String email = sc.next();

        System.out.println("Enter password");
        String password = sc.next();
        User user = new User(email, password, magazin);
        magazin.dobaviUser(user);
    }


    public void listAllOrders() {
        for (Stoka st : this.stokiVKolichka) {
            System.out.println(st);
        }
        System.out.println("Your total sum is " + this.getSumOrder() + "$");
        System.out.println();
    }

    private void changePassword() {
        System.out.println("Old password: ");
        String oldPassword = sc.next();
            while (oldPassword.equals(this.password)) {
                System.out.println("It's not your old password!");
                System.out.println("Try again: ");
                oldPassword = sc.next();
            }
            System.out.println("Successful!");
            String newPass = sc.next();
            this.setPassword(newPass);
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
        return "[email -> " + email + ", password -> " + password + "]";
    }

    public Profile getOwnProfile() {
        return ownProfile;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

    public int getSumOrder() {
        return sumOrder;
    }
}