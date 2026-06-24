package com.pbo.arungi.repository;

import com.pbo.arungi.model.Booking;
import com.pbo.arungi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBooking(Booking booking);
}
