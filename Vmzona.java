package vmzona;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Vmzona {

    public static final int MIN_VOTE = 1;
    public static final int MAX_VOTE = 5;
    private String ime;
    private int oborot;
    private Map<Kategoriq, TreeMap<Integer, Stoka>> stoki = new HashMap<Kategoriq, TreeMap<Integer, Stoka>>();
    private Map<String, User> users = new HashMap<String, User>();
    private List<Dostavchik> dostavchici = new ArrayList<Dostavchik>(Arrays.asList(new Dostavchik("Mircho"),
            new Dostavchik("Pesho"),
            new Dostavchik("Lacho"),
            new Dostavchik("Kaloqn")));

    private List<Stoka> porachaniStoki = new ArrayList<Stoka>();

    public Vmzona(String ime) {
        this.ime = ime;
        this.oborot = 0;

    }


    public void dostavi() {
        Dostavchik randomDostavchik = (Dostavchik) getRandom(this.dostavchici);
        System.out.println("The provider " + randomDostavchik.getName() + " will receive your order within two days!");
    }

    private static Object getRandom(List list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public void addStoka(Kategoriq kategoriq, Stoka stoka) {
        if (!stoki.containsKey(kategoriq)) {
            stoki.put(kategoriq, new TreeMap<>());
        }
        stoki.get(kategoriq).put(stoka.getNomerNaStoka(), stoka);
    }

    public Stoka remoteStoka(int nomerNaStoka) throws Exception {
        for (Map.Entry<Kategoriq, TreeMap<Integer, Stoka>> entry : stoki.entrySet()) {
            if (entry.getValue().containsKey(nomerNaStoka)) {
                Stoka stoka = entry.getValue().get(nomerNaStoka);
                entry.getValue().remove(nomerNaStoka);
                this.oborot += stoka.getCena();
                return stoka;
            }
        }
        throw new Exception("The stock not exist");
    }

    Scanner sc = new Scanner(System.in);

    public void izkaraiVsichkiNalichniStoki() {
        System.out.println("Choose criterion for sorting: ");
        System.out.println("1 - price");
        System.out.println("2 - name");
        System.out.println("3 - unique number of stock");
        String kriterij = sc.next();

        for (Map.Entry<Kategoriq, TreeMap<Integer, Stoka>> entry : stoki.entrySet()) {
            System.out.println("kategoriq " + entry.getKey());
            System.out.println("------------------");
            switch (kriterij) {
                case "1":
                    entry.getValue().values().stream().sorted((stoka1, stoka2) -> {
                        if (stoka1.getCena() != stoka2.getCena()) {
                            return stoka1.getCena() - stoka2.getCena();
                        }
                        return stoka1.getIme().compareTo(stoka2.getIme());
                    }).forEach(stoka -> System.out.println(stoka));
                    System.out.println();
                    continue;
                case "2":
                    entry.getValue().values().stream().sorted((stoka1, stoka2) -> {
                        if (stoka1.getIme().compareTo(stoka2.getIme()) == 0) {
                            return stoka1.getCena() - stoka2.getCena();
                        }
                        return stoka1.getIme().compareTo(stoka2.getIme());
                    }).forEach(stoka -> System.out.println(stoka));
                    System.out.println();
                    continue;
                case "3":
                    entry.getValue().values().stream().forEach(stoka -> System.out.println(stoka));
                    System.out.println();
                    continue;
                default:
                    System.out.println("Incorrect number!");
                    entry.getValue().values().stream().forEach(stoka -> System.out.println(stoka));
            }
            System.out.println();
        }
    }

    public void printAllProviders() {
        for (Dostavchik d : dostavchici) {
            System.out.println(d);
        }
    }

    public boolean proveriZaStoka(String nomer) {
        if (proweriZaKorektnostNaNomer(nomer)) {
            for (Map.Entry<Kategoriq, TreeMap<Integer, Stoka>> entry : stoki.entrySet()) {
                if (entry.getValue().containsKey(Integer.parseInt(nomer))) {
                    return true;
                }
            }
        }
        System.out.println("there is no commodity with such a number !");
        return false;
    }

    public void addOrders(Stoka stoka) {
        porachaniStoki.add(stoka);
    }

    public void dobaviUser(User user) {
        if (!users.containsKey(user.getEmail())) {
            users.put(user.getEmail(), user);
        }
    }

    public void premahniUser(String email) {
        if (users.containsKey(email)) {
            users.remove(email);
        }
    }

    public void getOrderStocks() {
        int counter = 1;
        for (Stoka s : porachaniStoki) {
            System.out.println(counter + ". id: " + s.getNomerNaStoka() + " - " + s.getIme());
            counter++;
        }
    }

    public static void messageForVote() throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Are you rate our site with score of 1 to 5?");
        System.out.println("YES or NO");
        String chooseVote = sc.nextLine();
        if (chooseVote.equalsIgnoreCase("YES")) {
            System.out.println("Vote:");
            String dadenVote=sc.next();
            if(proweriZaKorektnostNaNomer(dadenVote)) {
                int vote = dadenVote.charAt(0)-'0';
                if (vote < MIN_VOTE) {
                    vote = MIN_VOTE;
                }
                if (vote > MAX_VOTE) {
                    vote = MAX_VOTE;
                }

                int currentNumberVoters = 0;
                int allVoters = 0;

                File votes = new File("files\\votes.txt");
                votes.getParentFile().mkdir();
                if (!votes.exists()) {
                    votes.createNewFile();
                    try (PrintWriter pw = new PrintWriter(votes, StandardCharsets.UTF_8);) {
                        pw.println(vote);
                        allVoters += vote;
                        pw.flush();
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                } else {
                    try (
                            Scanner vot = new Scanner(new FileInputStream(votes));) {
                        allVoters = vot.nextInt();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error: " + e.getMessage());
                    }
                    try (PrintWriter pw = new PrintWriter(votes, StandardCharsets.UTF_8);) {
                        allVoters += vote;
                        pw.println(allVoters);
                        pw.flush();
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                File broiGlasuvali = new File("files" + File.separator + "broiGlasuvali.txt");
                broiGlasuvali.getParentFile().mkdir();
                if (!broiGlasuvali.exists()) {
                    broiGlasuvali.createNewFile();
                    try (PrintWriter pw = new PrintWriter(broiGlasuvali, StandardCharsets.UTF_8);) {
                        pw.println(++currentNumberVoters);
                        pw.flush();
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                } else {

                    try (Scanner vot = new Scanner(new FileInputStream(broiGlasuvali));) {
                        currentNumberVoters = vot.nextInt();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error: " + e.getMessage());
                    }
                    try (PrintWriter pw = new PrintWriter(broiGlasuvali, StandardCharsets.UTF_8);) {
                        pw.println(++currentNumberVoters);
                        pw.flush();
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                }

                System.out.println("Thank you!");
                System.out.println("number of voters : " + currentNumberVoters);
                System.out.println("average vote in the Vmzona is : " + ((allVoters * 1.0) / currentNumberVoters));

            }else {
                try {
                    throw new RatingException("invalid rating");
                } catch (RatingException e) {
                    System.err.println(e.getMessage());

                }
            }
        }
            System.out.println("Bye, have a nice day and come again ;)");
    }

    private static boolean proweriZaKorektnostNaNomer(String nomer) {
        if(nomer!=null&&nomer.length()>0) {
            for (int i = 0; i < nomer.length(); i++) {
                if (nomer.charAt(i) < '0' || nomer.charAt(i) > '9') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Map<String, User> getUsers() {
        return Collections.unmodifiableMap(users);
    }


    public int getOborot() {
        return oborot;
    }
}