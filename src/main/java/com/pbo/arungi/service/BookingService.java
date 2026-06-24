package com.pbo.arungi.service;

import com.pbo.arungi.model.Booking;
import com.pbo.arungi.model.TravelPackage;
import com.pbo.arungi.model.User;
import com.pbo.arungi.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(User user, TravelPackage travelPackage, String fullName,
                                  String email, String phoneNumber, LocalDate departureDate, int travelers) {
        double totalPrice = travelPackage.getPrice() * travelers;
        Booking booking = new Booking(user, travelPackage, fullName, email, phoneNumber,
                departureDate, travelers, totalPrice, "WAITING_PAYMENT");
        return bookingRepository.save(booking);
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public Booking markAsPaid(Booking booking) {
        booking.setStatus("PAID");
        return bookingRepository.save(booking);
    }

    public boolean isOwnedBy(Booking booking, User user) {
        return booking.getUser() != null && user != null
                && booking.getUser().getId().equals(user.getId());
    }
}
