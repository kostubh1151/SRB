package controller;

import repository.BookingRepository;
import repository.ResourceRepository;
import repository.UserRepository;
import service.BookingService;
import service.Calculator;
import service.InputValidator;
import service.ReportService;
import service.ResourceService;
import service.UserService;

public class Main {
	public static void main(String[] args) {
		UserRepository userRepository = new UserRepository();
		ResourceRepository resourceRepository = new ResourceRepository();
		BookingRepository bookingRepository = new BookingRepository();

		InputValidator validator = new InputValidator();
		Calculator calculator = new Calculator();
		UserService userService = new UserService(userRepository, validator);
		ResourceService resourceService = new ResourceService(resourceRepository, validator);
		BookingService bookingService = new BookingService(bookingRepository, calculator, validator);
		ReportService reportService = new ReportService(bookingRepository);
		DataReader dataReader = new DataReader(resourceService,userService);

		ConsoleUI consoleUI = new ConsoleUI(userService, resourceService, bookingService, reportService, validator,
				dataReader);
		consoleUI.start();
	}
}
