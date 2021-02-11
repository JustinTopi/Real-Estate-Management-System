import java.io.Serializable;

public class House extends Propertie implements Serializable {
	private int number_of_floors;
	private boolean has_garden = false;
	private boolean has_garage = false;

	// getters and setters
	public int getNumber_of_floors() {
		return number_of_floors;
	}

	public void setNumber_of_floors(int number_of_floors) {
		this.number_of_floors = number_of_floors;
	}

	public Boolean getHas_garden() {
		return has_garden;
	}

	public void setHas_garden(Boolean has_garden) {
		this.has_garden = has_garden;
	}

	public Boolean getHas_garage() {
		return has_garage;
	}

	public void setHas_garage(Boolean has_garage) {
		this.has_garage = has_garage;
	}

	public House(String address, int number_of_rooms, double selling_price, double sold_price, String branch_name,
			int number_of_floors, boolean has_garage, boolean has_garden) {
		super(address, number_of_rooms, selling_price, sold_price, branch_name);
		this.number_of_floors = number_of_floors;
		this.has_garage = has_garage;
		this.has_garden = has_garden;
		// TODO Auto-generated constructor stub
	}
}
