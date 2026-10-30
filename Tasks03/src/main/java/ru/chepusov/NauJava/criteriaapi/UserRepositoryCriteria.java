package ru.chepusov.NauJava.criteriaapi;

import jakarta.jws.soap.SOAPBinding;
import ru.chepusov.NauJava.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCriteria {
    User findByName(String name);
    User findById(int id);
    List<User> findByFirstAndLastNames(String firstName, String lastName);
}
