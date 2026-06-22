package com.pbo.arungi.controller;

import com.pbo.arungi.service.PackageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}