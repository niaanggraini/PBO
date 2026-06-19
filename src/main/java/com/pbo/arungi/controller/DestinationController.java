package com.pbo.arungi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DestinationController {

    @GetMapping("/destinations")
    public String destinations(){

        return "destinations";

    }

}