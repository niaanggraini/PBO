package com.pbo.arungi.controller;

import com.pbo.arungi.Model.User;
import com.pbo.arungi.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ProfileController {

private final UserRepository userRepository;

public ProfileController(UserRepository userRepository) {
    this.userRepository = userRepository;
}

@GetMapping("/profile")
public String profile(
        HttpSession session,
        Model model) {

    Boolean isLoggedIn =
            (Boolean) session.getAttribute("isLoggedIn");

    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/login";
    }

    String email =
            (String) session.getAttribute("userEmail");

    model.addAttribute("email", email);

    return "profile";
}

@GetMapping("/profile/settings")
public String settings(
        HttpSession session,
        Model model) {

    Boolean isLoggedIn =
            (Boolean) session.getAttribute("isLoggedIn");

    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/login";
    }

    String email =
            (String) session.getAttribute("userEmail");

    Optional<User> userOptional =
            userRepository.findByEmail(email);

    if (userOptional.isPresent()) {

        User user = userOptional.get();

        model.addAttribute(
                "fullName",
                user.getFullName());

        model.addAttribute(
                "email",
                user.getEmail());
    }

    return "profile-settings";
}

@PostMapping("/profile/settings")
public String saveSettings(
        @RequestParam String fullName,
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam String confirmPassword,
        HttpSession session,
        Model model) {

    Boolean isLoggedIn =
            (Boolean) session.getAttribute("isLoggedIn");

    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/login";
    }

    String currentEmail =
            (String) session.getAttribute("userEmail");

    Optional<User> userOptional =
            userRepository.findByEmail(currentEmail);

    if (userOptional.isEmpty()) {

        model.addAttribute(
                "error",
                "User tidak ditemukan!");

        return "profile-settings";
    }

    User user = userOptional.get();

    if (!password.isBlank()) {

        if (password.length() < 8) {

            model.addAttribute(
                    "error",
                    "Password minimal 8 karakter!");

            return "profile-settings";
        }

        if (!password.equals(confirmPassword)) {

            model.addAttribute(
                    "error",
                    "Password dan Confirm Password harus sama!");

            return "profile-settings";
        }

        user.setPassword(password);
    }

    user.setFullName(fullName);
    user.setEmail(email);

    userRepository.save(user);

    session.setAttribute(
            "userEmail",
            email);

    model.addAttribute(
            "success",
            "Profile updated successfully!");

    model.addAttribute(
            "email",
            email);

    model.addAttribute(
            "fullName",
            fullName);

    return "profile-settings";
}

}
