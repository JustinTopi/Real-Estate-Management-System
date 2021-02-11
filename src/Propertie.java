import java.io.Serializable;

public class Propertie implements Serializable {
	private String address;
	private int number_of_room;
	private double selling_price;
	private double sold_price;
	private String branch_name;

	//getters and setters
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumber_of_room() {
		return number_of_room;
	}

	public void setNumber_of_room(int number_of_rooms) {
		this.number_of_room = number_of_room;
	}

	public double getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(double selling_price) {
		this.selling_price = selling_price;
	}

	public double getSold_price() {
		return sold_price;
	}

	public void setSold_price(double sold_price) {
		this.sold_price = sold_price;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public Propertie(String address, int number_of_room, double selling_price, double sold_price, String branch_name) {
		super();
		this.address = address;
		this.number_of_room = number_of_room;
		this.selling_price = selling_price;
		this.sold_price = sold_price;
		this.branch_name = branch_name;
	}

}
