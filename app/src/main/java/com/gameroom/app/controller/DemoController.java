package com.gameroom.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String landingPage() {
        return "landing-page";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
