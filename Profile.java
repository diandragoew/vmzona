import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;

public class Profile {
	
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String city;
	private String phoneNumber;
	private String address;
	
	private static final String[] CITIES = {"Asenovgrad", "Aytos", "Bansko", "Blagoevgrad", "Burgas", "Dimitrovgrad", "Dobrich", "Gabrovo", 
											"Gotse Delchev", "Haskovo","Ivaylovgrad", "Kardzhali","Kyustendil","Lovech", "Montana", "Nesebar", 
											"Nova Zagora", "Pazardzhik","Pernik", "Petrich", "Pleven", "Plovdiv", "Primorsko", "Razgrad", "Ruse", 
											"Sandanski","Shumen", "Silistra","Slivn", "Smolyan", "Sofia City", "Sofia(province)", "Sozopol", 
											"Stara Zagora", "Targovishte", "Varna", "Veliko Tarnovo", "Velingrad", "Vidin", "Vratsa", "Yambol"};
	
	
	Scanner sc = new Scanner(System.in);
	
	public Profile() {
		
		this.setFirstName();
		this.setLastName();
		this.setBirthDate();
		this.setCity();
		this.setPhoneNumber();
		this.setAddress();
	}
	
	private static boolean checkForPhoneNumber(String phoneNumber) {
        String regex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}|\\(\\d{3}\\)-\\d{3}-?\\d{4}|\\(\\d{3}\\) \\d{3} ?\\d{4}|"
        				+ "\\(\\d{3}\\)-\\d{3} ?\\d{4}|\\(\\d{3}\\) \\d{3}-?\\d{4}";
        return phoneNumber != null && phoneNumber.trim().length() == 10 && phoneNumber.matches(regex);
    }
	
	/*private boolean checkForPhoneNumber(String phoneNumber) {
		return phoneNumber != null && phoneNumber.trim().length() == 10;
	}*/
	

	public void setFirstName() {
		System.out.println("First Name:");
		String firstName = sc.next();
		if (firstName != null) {
			this.firstName = firstName;
		}else {
			System.out.println("Please write First Name");
		}
	}
	
	
	public void setLastName() {
		System.out.println("Last Name:");
		String lastName = sc.next();
		if (lastName != null) {
			this.lastName = lastName;
		}else {
			System.out.println("Please write Last Name");
		}
	}

	private void validDay(int day) {
		do {
	        System.out.println("Enter correct day of birth: ");
	        day = sc.nextInt();
	    } while (day < 0 || day > 31);
	}
	
	private void validMonth(int month) {
		do {
	        System.out.println("Enter correct month of birth: ");
	        month = sc.nextInt();
	    } while (month < 0 || month > 12);
	}
	
	private void validYear(int year) {
		do {
	        System.out.println("Enter correct year of birth: ");
	        year = sc.nextInt();
	    } while (year < 0 || year > Year.now().getValue());
	}
	
	public void setBirthDate() {
		System.out.println("Your Birth Date: ");
		String date = sc.nextLine();
		String[] dayMonthYear = date.split("/");
		
		if(dayMonthYear.length == 3) {
			int day = Integer.parseInt(dayMonthYear[0]);
			int month = Integer.parseInt(dayMonthYear[1]);
			int year = Integer.parseInt(dayMonthYear[2]);
				
			this.validDay(day);
			this.validMonth(month);
			this.validYear(year);
			LocalDate locDate = LocalDate.of(year, month, day);
			this.birthDate = locDate;
		}
		else {
			System.out.println("Invalid date!"); 
		}
	}

	
	public void setCity() {
		System.out.println("Please choose your city:");
		for (int i = 0; i < CITIES.length; i++) {
			System.out.println(i + " - " + CITIES[i]);
		}
		int index = sc.nextInt();
		this.city = CITIES[index];
	}

	
	public void setPhoneNumber() {
		System.out.print("Phone number: ");
		String phoneNumber = sc.next();
		while (!checkForPhoneNumber(phoneNumber)) {
			System.err.print("Please enter a correct phone number: ");
			phoneNumber = sc.next();
		}
		this.phoneNumber = phoneNumber;
	}


	public void setAddress() {
		System.out.print("Your address: ");
		String address = sc.next();
		if (address != null) {
			this.address = address;
		}
	}



	@Override
	public String toString() {
		return "firstName: " + firstName + ", lastName: " + lastName + ", birthDate: " + birthDate + ", city:"
				+ city + ", address: " + address + ", phoneNumber:" + phoneNumber ;
	}
	
	
}