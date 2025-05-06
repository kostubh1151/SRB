package service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import entity.Booking;
import repository.BookingRepository;

public class ReportService {
	private BookingRepository bookingRepository;

	public ReportService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public Map<String, Long> getBookingTrends() {
		return bookingRepository.findAll().stream()
				.collect(Collectors.groupingBy(booking -> booking.getResource().getName(), Collectors.counting()));
	}

	public List<Booking> getUserBookingHistory(String userId) {
		return bookingRepository.findByUserId(userId);
	}
}
