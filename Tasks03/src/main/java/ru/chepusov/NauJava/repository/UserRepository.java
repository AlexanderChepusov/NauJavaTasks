package ru.chepusov.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chepusov.NauJava.entities.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findById(int id);
    List<User> findByAgeLessThan(int age);
    List<User> findByFirstNameOrLastName(String first, String last);


}
