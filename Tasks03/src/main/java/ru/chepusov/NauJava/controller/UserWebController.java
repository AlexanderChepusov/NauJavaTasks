package ru.chepusov.NauJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.chepusov.NauJava.entities.User;
import ru.chepusov.NauJava.repository.UserRepository;

import java.util.List;

@Controller
public class UserWebController {
    private final UserRepository userRep;

    public UserWebController(UserRepository userRep) {
        this.userRep = userRep;
    }

    @GetMapping("users/view")
    public String showAll(Model model) {
        List<User> list = (List<User>) userRep.findAll();


        // Кладем список в модель под именем "usersList"
        model.addAttribute("usersList", list);
        return "users";
    }


}
