
import java.io.Serializable;
import java.util.ArrayList;

public class Branch implements Serializable {
	private String address;
	private String phone_number;
	private String email;
	private String name;
	private String web_address;
	private String username;
	private String password;
	ArrayList<Flat> Flats = new ArrayList<Flat>();
	ArrayList<House> Houses = new ArrayList<House>();
	private String secretaryName;

	public ArrayList<Flat> getFlats() {
		return Flats;
	}

	public ArrayList<House> getHouses() {
		return Houses;
	}

	// getters and setters
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeb_address() {
		return web_address;
	}

	public void setWeb_address(String web_address) {
		this.web_address = web_address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecretaryName() {
		return secretaryName;
	}

	public void setSecretaryName(String secretaryName) {
		this.secretaryName = secretaryName;
	}

	public Branch(String address, String phone_number, String email, String name, String web_address, String username,
			String password, ArrayList<House> houses, ArrayList<Flat> flats, String secretaryName) {
		super();
		this.address = address;
		this.phone_number = phone_number;
		this.email = email;
		this.name = name;
		this.web_address = web_address;
		this.username = username;
		this.password = password;
		this.Houses = houses;
		this.Flats = flats;
		this.secretaryName = secretaryName;
	}
}
