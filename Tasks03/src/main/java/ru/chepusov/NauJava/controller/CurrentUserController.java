package ru.chepusov.NauJava.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chepusov.NauJava.entities.User;

@Controller
@RequestMapping("/")
public class CurrentUserController {
    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal User currentUser, Model model) {
        // currentUser уже содержит данные авторизованного пользователя
        model.addAttribute("currentUser", currentUser);
        return "profile";
    }
}
