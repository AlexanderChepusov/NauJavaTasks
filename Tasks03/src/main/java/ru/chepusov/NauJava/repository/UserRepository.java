package ru.chepusov.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.chepusov.NauJava.entities.User;

import java.util.List;

@RepositoryRestResource //(path = "rest/users")
public interface UserRepository extends CrudRepository<User, Integer> {
    User findById(long id);
    List<User> findByAgeLessThan(int age);
    List<User> findByFirstNameOrLastName(String first, String last);

    default User findByLogin(String name) {
        return findByLoginIgnoreCase(name);
    }
    User findByLoginIgnoreCase(String name);

    @Query("select u.id from User u where u.password is null")
    List<Long> listOfNullPassword();


}
