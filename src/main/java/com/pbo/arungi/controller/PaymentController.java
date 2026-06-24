package com.pbo.arungi.controller;

import com.pbo.arungi.model.Booking;
import com.pbo.arungi.model.User;
import com.pbo.arungi.repository.BookingRepository;
import com.pbo.arungi.repository.PaymentRepository;
import com.pbo.arungi.repository.UserRepository;
import com.pbo.arungi.service.BookingService;
import com.pbo.arungi.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PaymentController {

    private final PaymentService paymentService;
    private final BookingService bookingService;
    private final UserRepository userRepository;

    public PaymentController(PaymentService paymentService, BookingService bookingService, UserRepository userRepository) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;
        this.userRepository = userRepository;
    }

    @GetMapping("/payment")
    public String payment() {
        return "redirect:/my-bookings";
    }

    @GetMapping("/payment/{bookingId}")
    public String paymentForm(@PathVariable Long bookingId, HttpSession session, Model model) {

        Optional<User> userOptional = getLoggedInUser(session);
        if (userOptional.isEmpty()) return "redirect:/login";

        Optional<Booking> bookingOptional = bookingService.getBookingById(bookingId);
        if (bookingOptional.isEmpty() || !bookingService.isOwnedBy(bookingOptional.get(), userOptional.get())) {
            return "redirect:/my-bookings";
        }

        model.addAttribute("booking", bookingOptional.get());
        return "payment";
    }

    @PostMapping("/payment")
    public String confirmPayment(
            @RequestParam Long bookingId,
            @RequestParam String paymentMethod,
            HttpSession session,
            Model model) {

        Optional<User> userOptional = getLoggedInUser(session);
        if (userOptional.isEmpty()) return "redirect:/login";

        Optional<Booking> bookingOptional = bookingService.getBookingById(bookingId);
        if (bookingOptional.isEmpty() || !bookingService.isOwnedBy(bookingOptional.get(), userOptional.get())) {
            return "redirect:/my-bookings";
        }

        if (paymentMethod.isBlank()) {
            model.addAttribute("booking", bookingOptional.get());
            model.addAttribute("error", "Pilih metode pembayaran terlebih dahulu!");
            return "payment";
        }

        paymentService.createPayment(bookingOptional.get(), paymentMethod);
        return "redirect:/my-bookings";
    }

    private Optional<User> getLoggedInUser(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null || !isLoggedIn) return Optional.empty();
        String email = (String) session.getAttribute("userEmail");
        if (email == null || email.isBlank()) return Optional.empty();
        return userRepository.findByEmail(email);
    }
}
