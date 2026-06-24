package com.pbo.arungi.controller;

import com.pbo.arungi.model.TravelPackage;
import com.pbo.arungi.service.PackageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PackageController {

        private final PackageService packageService;

        public PackageController(
                        PackageService packageService) {

                this.packageService = packageService;
        }

        @GetMapping("/packages")
        public String packages(

                        @RequestParam(required = false) String destination,

                        @RequestParam(required = false) Integer duration,

                        @RequestParam(required = false) Double minPrice,

                        @RequestParam(required = false) Double maxPrice,

                        Model model) {

                List<TravelPackage> packages = packageService.filterPackages(
                                destination,
                                duration,
                                minPrice,
                                maxPrice);

                model.addAttribute(
                                "packages",
                                packages);

                model.addAttribute(
                                "selectedDestination",
                                destination);

                return "packages";
        }

        @GetMapping("/packages/detail")
        public String packageDetail(
                        HttpSession session) {

                Boolean isLoggedIn = (Boolean) session.getAttribute(
                                "isLoggedIn");

                if (isLoggedIn == null ||
                                !isLoggedIn) {

                        return "redirect:/login";
                }

                return "package-detail";
        }

        @GetMapping("/packages/detail/{id}")
        public String packageDetail(

                        @PathVariable Long id,

                        Model model,

                        HttpSession session) {

                Boolean isLoggedIn = (Boolean) session.getAttribute(
                                "isLoggedIn");

                if (isLoggedIn == null ||
                                !isLoggedIn) {

                        return "redirect:/login";
                }

                TravelPackage travelPackage = packageService
                                .getPackageById(id);

                model.addAttribute(
                                "packageData",
                                travelPackage);

                return "package-detail";
        }

}