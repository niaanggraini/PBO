package com.pbo.arungi.controller;

import com.pbo.arungi.repository.DestinationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DestinationController {

    private final DestinationRepository destinationRepository;

    public DestinationController(
            DestinationRepository destinationRepository) {

        this.destinationRepository = destinationRepository;
    }

    @GetMapping("/destinations")
    public String destinations(Model model) {

        model.addAttribute(
                "destinations",
                destinationRepository.findAll());

        return "destinations";
    }
}