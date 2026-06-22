package com.pbo.arungi.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("isLoggedIn")
    public boolean isLoggedIn(HttpSession session) {

        Boolean loggedIn =
                (Boolean) session.getAttribute("isLoggedIn");

        return loggedIn != null && loggedIn;
    }
}