package com.pbo.arungi.controller;

import com.pbo.arungi.model.Destination;
import com.pbo.arungi.repository.DestinationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DestinationController {

        private final DestinationRepository destinationRepository;

        public DestinationController(
                        DestinationRepository destinationRepository) {

                this.destinationRepository = destinationRepository;
        }

        @GetMapping("/destinations")
        public String destinations(

                        @RequestParam(required = false) String keyword,

                        @RequestParam(required = false) String category,

                        Model model) {

                List<Destination> destinations;

                if (keyword != null &&
                                !keyword.isBlank()) {

                        destinations = destinationRepository
                                        .findByNameContainingIgnoreCase(
                                                        keyword);

                }

                else if (category != null &&
                                !category.isBlank()) {

                        destinations = destinationRepository
                                        .findByCategory(category);

                }

                else {

                        destinations = destinationRepository.findAll();

                }

                model.addAttribute(
                                "destinations",
                                destinations);

                model.addAttribute(
                                "keyword",
                                keyword);

                model.addAttribute(
                                "selectedCategory",
                                category);

                return "destinations";
        }
}