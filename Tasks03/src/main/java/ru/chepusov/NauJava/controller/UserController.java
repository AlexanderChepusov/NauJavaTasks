package ru.chepusov.NauJava.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.chepusov.NauJava.entities.User;
import ru.chepusov.NauJava.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {
    private final UserRepository userRep;

    public UserController(UserRepository userRep) {
        this.userRep = userRep;
    }

    @GetMapping
    public List<User> findAllUsers() {
        return (List<User>) userRep.findAll();
    }

    @GetMapping("/list")
    @Operation(summary = "Выводит пользователей нужного возраста")
    public List<User> userListView(@RequestParam(required = false, defaultValue = "80") int age) {
        return userRep.findByAgeLessThan(age);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Вернет код 201 (Created) при успехе
    public User addUser(@RequestBody User user) {
        System.out.println("User " + user + " is saved!");
        return userRep.save(user);

    }


}
