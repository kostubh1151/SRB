package repository;

import java.util.ArrayList;
import java.util.List;

import entity.Booking;

public class BookingRepository {
	private List<Booking> bookings = new ArrayList<>();

	public void save(Booking booking) {
		bookings.add(booking);
	}

	public List<Booking> findAll() {
		return new ArrayList<>(bookings);
	}

	public List<Booking> findByUserId(String userId) {
		return bookings.stream().filter(booking -> booking.getUser().getId().equals(userId)).toList();
	}

	public Booking findById(String bookingId) {
		return bookings.stream().filter(booking -> booking.getId().equals(bookingId)).findFirst().orElse(null);
	}

	public void delete(String bookingId) {
		bookings.removeIf(booking -> booking.getId().equals(bookingId));
	}
}
