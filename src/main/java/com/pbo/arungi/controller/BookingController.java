package com.pbo.arungi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {


    @GetMapping("/booking")
    public String booking() {

        return "booking";

    }


    @GetMapping("/my-bookings")
    public String myBookings() {

        return "my-bookings";

    }

}