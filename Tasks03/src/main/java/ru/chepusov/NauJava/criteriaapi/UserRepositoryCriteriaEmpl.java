package ru.chepusov.NauJava.criteriaapi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.chepusov.NauJava.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryCriteriaEmpl implements UserRepositoryCriteria {
    private final EntityManager entityManager;

    //@Autowired
    public UserRepositoryCriteriaEmpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        Predicate namePredicate = criteriaBuilder.equal(userRoot.get("login"), name);

        criteriaQuery.select(userRoot).where(namePredicate);

        return entityManager.createQuery(criteriaQuery).getSingleResult();

        /*
        public Optional<User> findUserByLogin(String login) {
    // ... построение запроса ...

    try {
        User user = entityManager.createQuery(criteriaQuery).getSingleResult();
        return Optional.of(user);
    } catch (NoResultException e) {
        return Optional.empty(); // Если не нашли, возвращаем пустой Optional
    }
}
         */
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findByFirstAndLastNames(String firstName, String lastName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        Predicate fNamePredicate = criteriaBuilder.equal(userRoot.get("firstName"), firstName);
        Predicate lNamePredicate = criteriaBuilder.equal(userRoot.get("lastName"), lastName);

        criteriaQuery.where(criteriaBuilder.or(fNamePredicate, lNamePredicate));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
