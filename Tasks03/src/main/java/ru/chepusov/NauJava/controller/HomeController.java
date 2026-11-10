package ru.chepusov.NauJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/index", "/index"})
public class HomeController {

    @GetMapping
    public String goHomepage() {
        return "home";
    }

    @GetMapping("/login")
    public String goToLogin() {
        return "login";
    }
}
