package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	public void writeListToFile(List<Booking> list, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Booking line : list) {
                writer.write(line.toString());
                writer.newLine();
            }
            System.out.println("generated report");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
	
}
