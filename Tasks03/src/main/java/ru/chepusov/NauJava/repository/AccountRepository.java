package ru.chepusov.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.chepusov.NauJava.entities.Account;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query("select a from Account a where id = ?1")
    Optional<Account> findById(Long id);

    List<Account> findByUserId(Long id);

    //List<Account> findAll();
}
