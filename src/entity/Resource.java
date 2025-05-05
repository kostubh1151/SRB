package entity;

public class Resource {
	private String id;
	private String name;
	private String type;
	private double costPerHour;
	private boolean isAvailable;

	public Resource(String id, String name, String type, double costPerHour) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.costPerHour = costPerHour;
		this.isAvailable = true;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public double getCostPerHour() {
		return costPerHour;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
	}
}
