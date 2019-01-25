package vmzona;

import java.util.HashMap;
import java.util.Set;

public class User {
    private String email;
    private String password;
    private Set<Stoka> stokiVKolichka = new Set<Stoka>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
	
	public void buySomething(Stoka st) {
		if (st != null)
			this.stokiVKolichka.add(st);
		else
			System.out.println("Stoka is null!");
	}
	
	public void listAllOrders() {
		for (Stoka st : this.stokiVKolichka) {
			System.out.println(st);
		}
	}
	
	@Override
	public boolean equals(Object otherUser) {
		if (otherUser instanceof User)
			return this.email.equals(((User)otherUser).email);
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
}
