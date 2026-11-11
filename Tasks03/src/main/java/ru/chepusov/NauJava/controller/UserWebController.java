package ru.chepusov.NauJava.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.chepusov.NauJava.entities.Role;
import ru.chepusov.NauJava.entities.User;
import ru.chepusov.NauJava.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserWebController {
    private final UserRepository userRep;

    public UserWebController(UserRepository userRep) {
        this.userRep = userRep;
    }

    @GetMapping("/view")
    public String showAll(Model model) {
        List<User> list = (List<User>) userRep.findAll();
        // Кладем список в модель под именем "usersList"
        model.addAttribute("usersList", list);
        return "users";
    }

    @GetMapping("users/add")
    public String addNewUser(Model model) { //@ModelAttribute User user) {
        User user = new User();
        model.addAttribute("user", user);
        return "userCreation";
    }

    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable long id, Model model) {
        User user = userRep.findById(id);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile")
    public String showCurrentUser(Model model) {
        // Получаем логин текущего авторизованного пользователя
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();

        // Ищем пользователя в базе по логину
        User user = userRep.findByLogin(login);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/list")
    public String showAllUsers(Model model) {
        List<User> list = (List<User>) userRep.findAll();
        model.addAttribute("usersList", list);
        return "users";
    }

    @PostMapping ("users/new")
    public String createNewUser(@ModelAttribute User user) { //@ModelAttribute User user) {
        //User user = new User();
        userRep.save(user);
        //model.addAttribute(user);
        return "redirect:/users/view";
    }
}
