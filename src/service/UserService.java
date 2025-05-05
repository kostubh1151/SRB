package service;

import entity.Admin;
import entity.RegularUser;
import entity.ResourceManager;
import entity.User;
import repository.UserRepository;

public class UserService {
	private UserRepository userRepository;
	private InputValidator validator;

	public UserService(UserRepository userRepository, InputValidator validator) {
		this.userRepository = userRepository;
		this.validator = validator;
	}

	public void registerUser(String id, String username, String password, String role) {
		if (!validator.validateUsername(username)) {
			throw new IllegalArgumentException("Invalid username format");
		}
		if (!validator.validatePassword(password)) {
			throw new IllegalArgumentException("Invalid password format");
		}
		if (userRepository.findByUsername(username) != null) {
			throw new IllegalArgumentException("Username already exists");
		}

		User user;
		switch (role.toUpperCase()) {
		case "ADMIN":
			user = new Admin(id, username, password);
			break;
		case "RESOURCE_MANAGER":
			user = new ResourceManager(id, username, password);
			break;
		case "REGULAR_USER":
			user = new RegularUser(id, username, password);
			break;
		default:
			throw new IllegalArgumentException("Invalid role: " + role);
		}
		userRepository.save(user);
	}

	public User login(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user != null && user.authenticate(password)) {
			return user;
		}
		return null;
	}
}