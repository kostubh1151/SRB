package entity;

public class Admin extends User {
	public Admin(String id, String username, String password) {
		super(id, username, password, "ADMIN");
	}
}
