package com.pbo.arungi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PackageController {


    @GetMapping("/packages")
    public String packages() {

        return "packages";

    }


    @GetMapping("/packages/detail")
    public String packageDetail() {

        return "package-detail";

    }

}