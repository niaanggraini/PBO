package com.pbo.arungi.controller;

import com.pbo.arungi.model.Booking;
import com.pbo.arungi.model.TravelPackage;
import com.pbo.arungi.model.User;
import com.pbo.arungi.repository.UserRepository;
import com.pbo.arungi.service.BookingService;
import com.pbo.arungi.service.PackageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class BookingController {

    private final BookingService bookingService;
    private final PackageService packageService;
    private final UserRepository userRepository;

    public BookingController(BookingService bookingService, PackageService packageService,
            UserRepository userRepository) {
        this.bookingService = bookingService;
        this.packageService = packageService;
        this.userRepository = userRepository;
    }

    @GetMapping("/booking")
    public String booking() {
        return "redirect:/packages";
    }

    @GetMapping("/booking/{packageId}")
    public String bookingForm(@PathVariable Long packageId, HttpSession session, Model model) {
        Optional<User> userOptional = getLoggedInUser(session);
        if (userOptional.isEmpty())
            return "redirect:/login";

        TravelPackage travelPackage = packageService.getPackageById(packageId);
        if (travelPackage == null)
            return "redirect:/packages";

        model.addAttribute("packageData", travelPackage);
        model.addAttribute("fullName", userOptional.get().getFullName());
        model.addAttribute("email", userOptional.get().getEmail());
        return "booking";
    }

    @PostMapping("/booking")
    public String createBooking(
            @RequestParam Long packageId,
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam int travelers,
            HttpSession session, Model model) {

        Optional<User> userOptional = getLoggedInUser(session);
        if (userOptional.isEmpty())
            return "redirect:/login";

        TravelPackage travelPackage = packageService.getPackageById(packageId);
        if (travelPackage == null)
            return "redirect:/packages";

        if (fullName.isBlank() || email.isBlank() || phoneNumber.isBlank() || departureDate == null || travelers < 1) {
            model.addAttribute("packageData", travelPackage);
            model.addAttribute("fullName", fullName);
            model.addAttribute("email", email);
            model.addAttribute("phoneNumber", phoneNumber);
            model.addAttribute("travelers", travelers);
            model.addAttribute("error", "Semua data wajib diisi dengan benar!");
            return "booking";
        }

        Booking booking = bookingService.createBooking(
                userOptional.get(), travelPackage, fullName, email, phoneNumber, departureDate, travelers);

        return "redirect:/payment/" + booking.getId();
    }

    @GetMapping("/my-bookings")
    public String myBookings(HttpSession session, Model model) {
        Optional<User> userOptional = getLoggedInUser(session);
        if (userOptional.isEmpty())
            return "redirect:/login";

        model.addAttribute("bookings", bookingService.getBookingsByUser(userOptional.get()));
        return "my-bookings";
    }

    private Optional<User> getLoggedInUser(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null || !isLoggedIn)
            return Optional.empty();
        String email = (String) session.getAttribute("userEmail");
        if (email == null || email.isBlank())
            return Optional.empty();
        return userRepository.findByEmail(email);
    }
}
