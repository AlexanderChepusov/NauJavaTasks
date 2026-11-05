package ru.chepusov.NauJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("users/add")
    public String addNewUser(Model model) { //@ModelAttribute User user) {
        User user = new User();
        model.addAttribute("user", user);
        return "userCreation";
    }

    @PostMapping ("users/new")
    public String createNewUser(@ModelAttribute User user) { //@ModelAttribute User user) {
        //User user = new User();
        userRep.save(user);
        //model.addAttribute(user);
        return "redirect:/users/view";
    }






}
