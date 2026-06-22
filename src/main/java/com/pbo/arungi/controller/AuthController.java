package com.pbo.arungi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        if (email.isBlank() || password.isBlank()) {

            model.addAttribute("error",
                    "Email dan Password wajib diisi!");

            return "login";
        }

        if (password.length() < 8) {

            model.addAttribute("error",
                    "Password minimal 8 karakter!");

            return "login";
        }

        System.out.println("=== LOGIN ===");
        System.out.println("Email : " + email);

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

            model.addAttribute("error",
                    "Semua field wajib diisi!");

            return "register";
        }

        if (password.length() < 8) {

            model.addAttribute("error",
                    "Password minimal 8 karakter!");

            return "register";
        }

        if (!password.equals(confirmPassword)) {

            model.addAttribute("error",
                    "Password dan Confirm Password harus sama!");

            return "register";
        }

        System.out.println("=== REGISTER ===");
        System.out.println("Nama : " + fullName);
        System.out.println("Email : " + email);

        model.addAttribute("success",
                "Registrasi berhasil! Silakan login.");

        return "login";
    }
}