package ru.chepusov.NauJava.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.chepusov.NauJava.entities.Role;
import ru.chepusov.NauJava.entities.User;
import ru.chepusov.NauJava.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/register")
class UserRegistrationController {
    private final UserRepository userRep;
    private PasswordEncoder passwordEncoder;

    public UserRegistrationController(UserRepository userRep, PasswordEncoder passwordEncoder) {
        this.userRep = userRep;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping//("auth")
    public String registerNewUser(Model model) {
        User user = new User();
        user.setRoles(List.of(Role.USER));
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute User user, @RequestParam("confirm") String confirm) {
        if (!user.getPassword().equals(confirm)) throw new IllegalArgumentException("Passwords are not match!!!");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRep.save(user);
        return "redirect:/users/view";  //return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        // Получаем информацию о текущем пользователе
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Если пользователь авторизован (и это не анонимный пользователь)
        if (auth != null && auth.isAuthenticated() &&
                !(auth.getPrincipal() instanceof String && auth.getPrincipal().equals("anonymousUser"))) {

            // Возвращаем шаблон для авторизованных (с кнопкой выхода)
            return "profile"; // Имя вашего шаблона, например, profile.html
        }

        // Иначе возвращаем обычную страницу логина
        return "login"; // login.html
    }



}
