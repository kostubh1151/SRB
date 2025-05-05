package service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Calculator {
	public double calculateCost(double costPerHour, LocalDateTime startTime, LocalDateTime endTime) {
		long hours = ChronoUnit.HOURS.between(startTime, endTime);
		if (hours <= 0) {
			throw new IllegalArgumentException("Booking duration must be positive");
		}
		return costPerHour * hours;
	}
}
