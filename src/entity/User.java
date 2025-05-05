package entity;

public abstract class User {
private String id;
private String username;
private String password;
private String role;

public User(String id, String username, String password, String role) {
this.id = id;
this.username = username;
this.password = password;
this.role = role;
}

public String getId() {
return id;
}

public String getUsername() {
return username;
}

public String getRole() {
return role;
}

public boolean authenticate(String password) {
return this.password.equals(password);
}
}
