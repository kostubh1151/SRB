package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import entity.Resource;

public class InputValidator {
	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

	public boolean validateUsername(String username) {
		return username != null && username.matches("^[a-zA-Z0-9]{3,20}$");
	}

	public boolean validatePassword(String password) {
		return password != null && password.length() >= 6;
	}

	public boolean validateResource(Resource resource) {

		return resource != null && resource.getName() != null && !resource.getName().trim().isEmpty()
				&& resource.getName().matches("^(?=.*[a-zA-Z])[a-zA-Z0-9 ]{3,50}$") && resource.getType() != null
				&& !resource.getType().isEmpty() && resource.getType().matches("^(?=.*[a-zA-Z])[a-zA-Z0-9 ]{3,50}$")
				&& resource.getCostPerHour() > 0;
	}

	public boolean validateDateTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
		return startTime != null && endTime != null && !startTime.isAfter(endTime)
				&& !startTime.isBefore(LocalDateTime.now());
	}

	public LocalDateTime parseDateTime(String dateTimeStr) {
		try {
			return LocalDateTime.parse(dateTimeStr, FORMATTER);
		} catch (DateTimeParseException e) {
			return null;
		}
	}
}