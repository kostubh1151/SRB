package repository;

import java.util.HashMap;
import java.util.Map;
import entity.User;

public class UserRepository {
private Map<String, User> users = new HashMap<>();

public void save(User user) {
users.put(user.getId(), user);
}

public User findById(String id) {
return users.get(id);
}

public User findByUsername(String username) {
return users.values().stream()
.filter(user -> user.getUsername().equals(username))
.findFirst()
.orElse(null);
}

public Map<String, User> findAll() {
return new HashMap<>(users);
}
}
