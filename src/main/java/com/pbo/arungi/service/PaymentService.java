package com.pbo.arungi.service;

import com.pbo.arungi.model.Booking;
import com.pbo.arungi.model.Payment;
import com.pbo.arungi.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingService bookingService;

    public PaymentService(PaymentRepository paymentRepository, BookingService bookingService) {
        this.paymentRepository = paymentRepository;
        this.bookingService = bookingService;
    }

    @Transactional
    public Payment createPayment(Booking booking, String paymentMethod) {
        Optional<Payment> existing = paymentRepository.findByBooking(booking);
        if (existing.isPresent()) {
            if (!"PAID".equals(booking.getStatus()))
                bookingService.markAsPaid(booking);
            return existing.get();
        }
        Payment payment = new Payment(booking, paymentMethod, booking.getTotalPrice(), "PAID");
        bookingService.markAsPaid(booking);
        return paymentRepository.save(payment);
    }
}
