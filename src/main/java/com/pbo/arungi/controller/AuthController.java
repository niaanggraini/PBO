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
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        if (email.isBlank() || password.isBlank()) {

            model.addAttribute(
                    "error",
                    "Email dan Password wajib diisi!");

            return "login";
        }

        Optional<User> userOptional =
                userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {

            model.addAttribute(
                    "error",
                    "Email tidak terdaftar!");

            return "login";
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(password)) {

            model.addAttribute(
                    "error",
                    "Password salah!");

            return "login";
        }

        session.setAttribute(
                "isLoggedIn",
                true);

        session.setAttribute(
                "userEmail",
                email);

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String processRegister(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model) {

        if (fullName.isBlank()
                || email.isBlank()
                || password.isBlank()
                || confirmPassword.isBlank()) {

            model.addAttribute(
                    "error",
                    "Semua field wajib diisi!");

            return "register";
        }

        if (password.length() < 8) {

            model.addAttribute(
                    "error",
                    "Password minimal 8 karakter!");

            return "register";
        }

        if (!password.equals(confirmPassword)) {

            model.addAttribute(
                    "error",
                    "Password dan Confirm Password harus sama!");

            return "register";
        }

        if (userRepository.findByEmail(email).isPresent()) {

            model.addAttribute(
                    "error",
                    "Email sudah terdaftar!");

            return "register";
        }

        User user = new User(
                fullName,
                email,
                password);

        userRepository.save(user);

        model.addAttribute(
                "success",
                "Registrasi berhasil! Silakan login.");

        return "login";
    }

    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}