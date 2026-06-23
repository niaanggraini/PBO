package com.pbo.arungi.controller;

import com.pbo.arungi.Model.TravelPackage;
import com.pbo.arungi.Service.PackageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PackageController {

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/packages")
    public String packages(Model model) {

        model.addAttribute("packages", packageService.getAllPackages());

        return "packages";
    }

    @GetMapping("/packages/detail")
    public String packageDetail(HttpSession session) {

        Boolean isLoggedIn =
                (Boolean) session.getAttribute("isLoggedIn");

        if (isLoggedIn == null || !isLoggedIn) {
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
            (Boolean) session.getAttribute("isLoggedIn");

    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/login";
    }

    TravelPackage travelPackage =
            packageService.getPackageById(id);

    model.addAttribute(
            "packageData",
            travelPackage);

    return "package-detail";
}
}