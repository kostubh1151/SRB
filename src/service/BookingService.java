package service;

import java.time.LocalDateTime;

import entity.Booking;
import entity.DateTimeRange;
import entity.Resource;
import entity.User;
import repository.BookingRepository;

public class BookingService {
	public BookingRepository bookingRepository;
	private Calculator calculator;
	private InputValidator validator;

	public BookingService(BookingRepository bookingRepository, Calculator calculator, InputValidator validator) {
		this.bookingRepository = bookingRepository;
		this.calculator = calculator;
		this.validator = validator;
	}

	public boolean isResourceAvailable(Resource resource, DateTimeRange range) {
		return bookingRepository.findAll().stream()
				.filter(booking -> booking.getResource().getId().equals(resource.getId()))
				.noneMatch(booking -> new DateTimeRange(booking.getStartTime(), booking.getEndTime()).overlaps(range));
	}

	public Booking createBooking(String id, User user, Resource resource, LocalDateTime startTime,
			LocalDateTime endTime) {
		if (!validator.validateDateTimeRange(startTime, endTime)) {
			throw new IllegalArgumentException("Invalid date/time range");
		}
		DateTimeRange range = new DateTimeRange(startTime, endTime);
		if (isResourceAvailable(resource, range)) {
			double cost = calculator.calculateCost(resource.getCostPerHour(), startTime, endTime);
			Booking booking = new Booking(id, user, resource, startTime, endTime, cost);
			bookingRepository.save(booking);
			resource.setAvailable(false);
			return booking;
		}
		return null;
	}

	public void cancelBooking(String bookingId) {
		Booking booking = bookingRepository.findById(bookingId);
		if (booking == null) {
			throw new IllegalArgumentException("Booking not found");
		}
		booking.getResource().setAvailable(true);
		bookingRepository.delete(bookingId);
	}
}