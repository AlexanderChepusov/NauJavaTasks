package ru.chepusov.NauJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.chepusov.NauJava.entities.User;
import ru.chepusov.NauJava.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class RController {
    private final UserRepository userRep;

    public RController(UserRepository userRep) {
        this.userRep = userRep;
    }

    @GetMapping("/list")
    public List<User> userListView(@RequestParam(required = false, defaultValue = "80") int age) {
        return userRep.findByAgeLessThan(age);
    }


}
