package com.pbo.arungi.repository;

import com.pbo.arungi.model.Booking;
import com.pbo.arungi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserOrderByCreatedAtDesc(User user);
}
