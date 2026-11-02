package ru.chepusov.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.chepusov.NauJava.entities.User;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Integer> {
    User findById(int id);
    List<User> findByAgeLessThan(int age);
    List<User> findByFirstNameOrLastName(String first, String last);

    User findByLogin(String name);


}
