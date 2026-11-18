package ru.chepusov.NauJava.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.chepusov.NauJava.entities.Account;
import ru.chepusov.NauJava.entities.User;
import ru.chepusov.NauJava.repository.AccountRepository;
import ru.chepusov.NauJava.repository.UserRepository;

import java.time.*;

@Controller
@RequestMapping("/")
public class CurrentUserController {
    private final UserRepository userRep;
    private final AccountRepository accRep;

    public CurrentUserController(UserRepository userRep, AccountRepository accRep) {
        this.userRep = userRep;
        this.accRep = accRep;
    }

    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal User currentUser, Model model) {
        // currentUser уже содержит данные авторизованного пользователя
        //currentUser = userRep.findByLogin(currentUser.getLogin());
        User freshUser = userRep.findByLogin(currentUser.getLogin());
        model.addAttribute("currentUser", freshUser);
        return "profile";
    }

    @PostMapping("/newAccount")
    public String createAccountForUser(@ModelAttribute Account acc, @RequestParam String expDate) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String)) {
            LocalDateTime localDate = LocalDate.parse(expDate).atStartOfDay();
            // Добавляем часовой пояс (например, +05:00 Екатеринбург)
            ZoneOffset offset = ZoneOffset.ofHours(5);
            // Создаем OffsetDateTime
            OffsetDateTime offsetDateTime = OffsetDateTime.of(localDate, offset);
            acc.setUser((User)auth.getPrincipal());
            acc.setExpirationDate(offsetDateTime);
            accRep.save(acc);
            return "redirect:/profile";
        }
        return "redirect:/login";
    }
}
