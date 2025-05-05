package entity;

public class RegularUser extends User {
	public RegularUser(String id, String username, String password) {
		super(id, username, password, "REGULAR_USER");
	}
}