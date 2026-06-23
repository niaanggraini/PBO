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

            @RequestParam(required = false)
            String destination,

            Model model) {

        List<TravelPackage> packages;

        if (destination != null &&
                !destination.isBlank()) {

            packages =
                    packageService
                    .getPackagesByDestination(
                            destination);

            model.addAttribute(
                    "selectedDestination",
                    destination);

        } else {

            packages =
                    packageService
                    .getAllPackages();

        }

        model.addAttribute(
                "packages",
                packages);

        return "packages";
    }

    @GetMapping("/packages/detail")
    public String packageDetail(
            HttpSession session) {

        Boolean isLoggedIn =
                (Boolean) session.getAttribute(
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

        Boolean isLoggedIn =
                (Boolean) session.getAttribute(
                        "isLoggedIn");

        if (isLoggedIn == null ||
                !isLoggedIn) {

            return "redirect:/login";
        }

        TravelPackage travelPackage =
                packageService
                        .getPackageById(id);

        model.addAttribute(
                "packageData",
                travelPackage);

        return "package-detail";
    }

}