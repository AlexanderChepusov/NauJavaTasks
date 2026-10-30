package ru.chepusov.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.chepusov.NauJava.entities.Account;
import ru.chepusov.NauJava.entities.User;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    @Query("select a from Account a where id = ?1")
    Account findById(int id);

    //List<Account> findAll();
}
