package ru.chepusov.NauJava.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.chepusov.NauJava.entities.User;

@ControllerAdvice
public class GlobalUserController {

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Если пользователь не авторизован (аноним), возвращаем null
        if (auth == null || !auth.isAuthenticated() ||
                auth.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        // Возвращаем вашего пользователя (он у вас реализует UserDetails)
        return (User) auth.getPrincipal();
    }
}

