package com.pbo.arungi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(){

        return "index";

    }


    @GetMapping("/destinations")
    public String destinations(){

        return "destinations";

    }


    @GetMapping("/packages")
    public String packages(){

        return "packages";

    }

    @GetMapping("/packages/detail")
    public String packageDetail(){

        return "package-detail";

    }

    @GetMapping("/login")
    public String login(){

        return "login";

    }


    @GetMapping("/register")
    public String register(){

        return "register";

    }

    @GetMapping("/about")
    public String about(){

        return "about";

    }

}