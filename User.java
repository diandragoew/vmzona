package vmzona;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String imeil;
    private String parola;
    private Map<Kategoriq,Stoka> stokiVKolichka=new HashMap<Kategoriq,Stoka>();

    public User(String imeil, String parola) {
        this.imeil = imeil;
        this.parola = parola;
    }
}
