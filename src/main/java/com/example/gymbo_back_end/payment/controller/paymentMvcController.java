package com.example.gymbo_back_end.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class paymentMvcController {

    @GetMapping("/toss")
    public String toss() {
        return "toss";
    }
}
