package controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import entity.Booking;
import entity.Cart;
import entity.Resource;
import entity.ResourceSelection;
import entity.User;
import repository.BookingRepository;
import repository.CartRepository;
import service.BookingService;
import service.InputValidator;
import service.ReportService;
import service.ResourceService;
import service.UserService;

public class ConsoleUI {
	private UserService userService;
	private ResourceService resourceService;
	private BookingService bookingService;
	private ReportService reportService;
	private InputValidator validator;
	private DataReader dataReader;
	private Scanner scanner;
	private User currentUser;
	private Cart cart;
	private CartRepository cartRepo;

	public ConsoleUI(UserService userService, ResourceService resourceService, BookingService bookingService,
			ReportService reportService, InputValidator validator, DataReader dataReader, CartRepository cart) {
		this.userService = userService;
		this.resourceService = resourceService;
		this.bookingService = bookingService;
		this.reportService = reportService;
		this.validator = validator;
		this.dataReader = dataReader;
		this.scanner = new Scanner(System.in);
		this.cart = new Cart();
		this.cartRepo = cart;

	}

	public void start() {
		dataReader.populateDummyData();
		while (true) {
			if (currentUser == null) {
				showLoginMenu();
			} else {
				showMainMenu();
			}
		}
	}

	private void showLoginMenu() {
		System.out.println("\n=== Smart Resource Booking & Management System ===");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Exit");
		System.out.print("Choose an option: ");
		try {
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				handleLogin();
				break;
			case 2:
				System.out.println("please login with admin role");
				handleLogin();
				if(currentUser.getRole().equals("ADMIN"))
				{
					
				handleRegister();
				}
				else
				{
					System.out.println("please login with admin role");
				}
				break;
			case 3:
				System.out.println("Exiting...");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option!");
			}
		} catch (Exception e) {
			System.out.println("Invalid input! Please enter a number.");
			scanner.nextLine();
		}
	}

	private void handleLogin() {
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		try {
			currentUser = userService.login(username, password);
			if (currentUser == null) {
				System.out.println("Invalid credentials!");
			} else {
				System.out.println("Login successful! Welcome, " + currentUser.getUsername());
				cart = cartRepo.getCartofParticularUser(currentUser);
				if (cart == null) {
					cart = new Cart();
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void handleRegister() {
		System.out.print("Enter username (alphanumeric, 3-20 characters): ");
		String username = scanner.nextLine();
		System.out.print("Enter password (at least 6 characters): ");
		String password = scanner.nextLine();
		String role;
		if (currentUser != null && currentUser.getRole().equals("ADMIN")) {
			System.out.print("Enter role (ADMIN, RESOURCE_MANAGER, REGULAR_USER): ");
			role = scanner.nextLine();
		} else {
			System.out.print("Enter role (ADMIN, RESOURCE_MANAGER): ");
			role = scanner.nextLine();
			while (role.toLowerCase().equals("regular_user")) {
				System.out.print("Invalid Role. Enter correct role:");
				role = scanner.nextLine();
			}
		}

		try {
			userService.registerUser(UUID.randomUUID().toString(), username, password, role);
			System.out.println("User registered successfully!");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void removeUser() {
		System.out.print("Enter the name of the user you want to remove:  ");
		String username = scanner.nextLine();
		if (bookingService.bookingRepository.findAll().isEmpty()) {
			userService.removeUser(username);
		} else {
			System.out.println("User has some bookings and cannot be removed.");
		}
	}

	private void showMainMenu() {
		if (currentUser.getRole().equals("ADMIN")) {
			System.out.println("\n=== SRBMS Main Menu ===");
			System.out.println("Role: " + currentUser.getRole());
			System.out.println("1. Browse Resource");
			System.out.println("2. Add User");
			System.out.println("3. Remove User");
			System.out.println("4. View User");
			System.out.println("5. View Reports");
			System.out.println("6. generate Report of Bookings");
			System.out.println("7. generate Report of Resources");
			System.out.println("8. Log Out");

			try {
				int choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 1:
					browseResources();
					break;
				case 2:
					handleRegister();
					break;
				case 3:
					removeUser();
					break;
				case 4:
					viewUser();
					break;
				case 5:
					viewReports();
					break;
				case 6:
					generateReport();
					break;
				case 7:
					generateReportForResources();
					break;
				case 8:
					currentUser = null;
					cart.clear();
					System.out.println("Logged out successfully!");
					break;

				default:
					System.out.println("Invalid option");
				}

			} catch (Exception e) {
				System.out.println("Invalid input! Please enter a number.");
				scanner.nextLine();
			}
		} else if (currentUser.getRole().equals("RESOURCE_MANAGER")) {
			System.out.println("\n=== SRBMS Main Menu ===");
			System.out.println("Role: " + currentUser.getRole());
			System.out.println("1. Add Resources");
			System.out.println("2. Update Resource");
			System.out.println("3. Delete Resource");
			System.out.println("4. View Resource");

			System.out.println("5. Log Out");

			try {
				int choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 1:
					addResource();
					break;
				case 2:
					updateResource();
					break;
				case 3:
					deleteResource();
					break;
				case 4:
					browseResources();
					break;
				case 5:
					currentUser = null;
					cart.clear();
					System.out.println("Logged out successfully!");
					break;
				default:
					System.out.println("Invalid option");
				}

			} catch (Exception e) {
				System.out.println("Invalid input! Please enter a number.");
				scanner.nextLine();
			}

		} else {
			System.out.println("\n=== SRBMS Main Menu ===");
			System.out.println("Role: " + currentUser.getRole());
			System.out.println("1. Browse Resources");
			System.out.println("2. Add to Cart");
			System.out.println("3. Confirm Booking");
			System.out.println("4. see All Bookings");
			System.out.println("5. Cancel Booking");
			System.out.println("6. view cart");
			System.out.println("7. remove from cart");
			System.out.println("8. clear cart");
			System.out.println("9. logout");
			System.out.print("Choose an option: ");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 1:
					browseResources();
					break;
				case 2:
					addToCart();
					break;
				case 3:
					confirmBooking();
					break;
				case 4:
					viewReports();
					break;
				case 5:
					cancelBooking();
					break;
				case 6:
					viewCart();
					break;
				case 7:
					removeFromCart();
					break;
				case 8:
					cart.clear();
					break;
				case 9:
					currentUser = null;
					// cart.clear();
					System.out.println("Logged out successfully!");
					break;
				default:
					System.out.println("Invalid option!");
				}
			} catch (Exception e) {
				System.out.println("Invalid input! Please enter a number.");
				scanner.nextLine();
			}
		}
	}

	private void generateReport() {
		String filePath = "src\\reports\\reports.txt";
		reportService.writeListToFile(bookingService.bookingRepository.findAll(), filePath);

	}

	private void generateReportForResources() {
		String filePath = "src\\reports\\Resourcereports.txt";
		reportService.writeListToFile(resourceService.getAllResources().values().stream().toList(), filePath);

	}

	private void viewUser() {
		System.out.println("\\n=== Available Users ===");
		userService.getAllUser().values()
				.forEach(user -> System.out.println("ID: " + user.getId() + ", Name: " + user.getUsername()));

	}

	private void browseResources() {
		System.out.println("\n=== Available Resources ===");
		resourceService.getAllResources().values()
				.forEach(resource -> System.out.println("ID: " + resource.getId() + ", Name: " + resource.getName()
						+ ", Type: " + resource.getType() + ", Cost/Hour: " + resource.getCostPerHour()
						+ ", Available: " + (resource.isAvailable() ? "Yes" : "No")));
	}

	private void addResource() {
		System.out.print("Enter resource name: ");
		String name = scanner.nextLine();
		System.out.print("Enter resource type: ");
		String type = scanner.nextLine();
		System.out.print("Enter cost per hour: ");
		double costPerHour;
		try {
			costPerHour = scanner.nextDouble();
			scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Invalid cost format! Please enter a number.");
			scanner.nextLine();
			return;
		}
		try {
			Resource resource = new Resource(UUID.randomUUID().toString(), name, type, costPerHour);
			resourceService.addResource(resource);
			System.out.println("Resource added successfully!");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void updateResource() {
		System.out.print("Enter resource ID: ");
		String id = scanner.nextLine();
		System.out.print("Enter new resource name: ");
		String newName = scanner.nextLine();
		System.out.print("Enter new resource type: ");
		String newType = scanner.nextLine();
		System.out.print("Enter new cost per hour: ");
		double newCost;
		try {
			newCost = scanner.nextDouble();
			scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Invalid cost format!");
			scanner.nextLine();
			return;
		}
		try {

			if (resourceService.getResource(id).isAvailable()) {
				resourceService.updateResource(id, newName, newType, newCost);
				System.out.println("Resource updated successfully!");
			} else {
				System.out.println("Resource is already booked So it cannot be updated");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void deleteResource() {
		System.out.print("Enter resource ID: ");
		String deleteId = scanner.nextLine();

		try {
			if (resourceService.getResource(deleteId).isAvailable()) {
				resourceService.deleteResource(deleteId);
				System.out.println("Resource deleted successfully!");
				return;
			}
			System.out.println("Resource is booked by users ");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void addToCart() {
		System.out.print("Enter resource ID: ");
		String resourceId = scanner.nextLine();
		Resource resource = resourceService.getResource(resourceId);
		if (resource == null) {
			System.out.println("Resource not found!");
			return;
		}
		System.out.print("Enter start time (yyyy-MM-dd HH:mm): ");
		String startStr = scanner.nextLine();
		System.out.print("Enter end time (yyyy-MM-dd HH:mm): ");
		String endStr = scanner.nextLine();
		try {
			LocalDateTime startTime = validator.parseDateTime(startStr);
			LocalDateTime endTime = validator.parseDateTime(endStr);
			if (startTime == null || endTime == null) {
				System.out.println("Invalid date/time format! Use yyyy-MM-dd HH:mm");
				return;
			}
			if (!validator.validateDateTimeRange(startTime, endTime)) {
				System.out.println("Invalid date/time range! Start time must be in the future and before end time.");
				return;
			}

			cart.addSelection(new ResourceSelection(resource, startTime, endTime));
			cartRepo.addCartRepository(currentUser, cart);

			System.out.println("Resource added to cart!");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		}
	}

	private void removeFromCart() {
		if (currentUser == null) {
			System.out.println("Please log in to remove items from your cart.");
			return;
		}

		System.out.print("Enter the Id of the resource to remove: ");
		String id = scanner.nextLine();

		ResourceSelection toRemove = null;
		for (ResourceSelection selection : cart.getSelections()) {
			if (selection.getResource().getId().equalsIgnoreCase(id)) {
				toRemove = selection;
				break;
			}
		}

		if (toRemove != null) {
			cart.getSelections().remove(toRemove);
			System.out.println(id + " has been removed from your cart.");
		} else {
			System.out.println("Resource not found in your cart.");
		}
	}

	private void viewCart() {
		if (currentUser == null) {
			System.out.println("Please log in to view your cart.");
			return;
		}

		List<ResourceSelection> selections = cart.getSelections();
		if (selections.isEmpty()) {
			System.out.println("Your cart is empty.");
		} else {
			System.out.println("Items in your cart:");
			for (ResourceSelection selection : selections) {
				System.out.println("Resource: " + selection.getResource().getName() + ", Start Time: "
						+ selection.getStartTime() + ", End Time: " + selection.getEndTime());
			}
		}
	}

	private boolean checkResourceAvail(ResourceSelection selection) {

		Map<String, Resource> map = resourceService.getAllResources();

		if (map.get(selection.getResource().getId()) != null) {
			return true;
		}

		return false;
	}

	private void confirmBooking() {
		if (cart.getSelections().isEmpty()) {
			System.out.println("Cart is empty!");
			return;
		}
		System.out.println("\n=== Confirm Bookings ===");
		for (ResourceSelection selection : cart.getSelections()) {

			if (checkResourceAvail(selection)) {

				try {
					Booking booking = bookingService.createBooking(UUID.randomUUID().toString(), currentUser,
							selection.getResource(), selection.getStartTime(), selection.getEndTime());

					if (booking != null) {
						System.out.println("Booking confirmed for " + selection.getResource().getName() + ", Cost: "
								+ booking.getCost());
					} else {
						System.out.println(
								"Booking failed for " + selection.getResource().getName() + " due to time conflict!");
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Error: " + e.getMessage());
				}
			} else {
				System.out.println(selection.getResource().getName() + " may not be available");
			}
		}
		cart.clear();
	}

	private void cancelBooking() {
		System.out.print("Enter booking ID: ");
		String bookingId = scanner.nextLine();
		try {
			bookingService.cancelBooking(bookingId, currentUser);

		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void viewReports() {
		if (currentUser.getRole() == "ADMIN") {
			System.out.println("\n=== Booking Trends ===");
			reportService.getBookingTrends()
					.forEach((resource, count) -> System.out.println("Resource: " + resource + ", Bookings: " + count));
		} else {
			System.out.println("\n=== Your Booking History ===");
			reportService.getUserBookingHistory(currentUser.getId())
					.forEach(booking -> System.out.println("Booking ID: " + booking.getId() + ", Resource: "
							+ booking.getResource().getName() + ", Start: " + booking.getStartTime() + ", End: "
							+ booking.getEndTime() + ", Cost: " + booking.getCost()));
		}
	}

}