package com.pbo.arungi.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}