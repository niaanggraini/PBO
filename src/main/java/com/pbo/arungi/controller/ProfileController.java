package com.pbo.arungi.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

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

        model.addAttribute("email", email);

        return "profile-settings";
    }

    @PostMapping("/profile/settings")
    public String saveSettings(
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

        model.addAttribute(
                "success",
                "Profile updated successfully!");

        return "profile-settings";
    }
}