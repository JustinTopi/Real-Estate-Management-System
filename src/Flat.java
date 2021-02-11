import java.io.Serializable;

public class Flat extends Propertie implements Serializable {

	private int floor;
	private double monthly_charge;

	public Flat(int number_of_rooms, double selling_price, double sold_price, String branch_name, String address,
			int floor, double monthly_charge) {
		super(address, number_of_rooms, selling_price, sold_price, branch_name);

		this.floor = floor;
		this.monthly_charge = monthly_charge;
	}

	// getters and setters
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public double getMonthly_charge() {
		return monthly_charge;
	}

	public void setMonthly_charge(double monthly_charge) {
		this.monthly_charge = monthly_charge;
	}

}
