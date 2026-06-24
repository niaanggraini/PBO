package com.pbo.arungi.controller;

import com.pbo.arungi.repository.DestinationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final DestinationRepository destinationRepository;

    public HomeController(
            DestinationRepository destinationRepository) {

        this.destinationRepository = destinationRepository;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "destinations",
                destinationRepository.findAll());

        return "index";
    }

}