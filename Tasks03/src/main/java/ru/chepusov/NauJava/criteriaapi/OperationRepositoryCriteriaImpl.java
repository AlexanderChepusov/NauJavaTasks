package ru.chepusov.NauJava.criteriaapi;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ru.chepusov.NauJava.entities.Category;
import ru.chepusov.NauJava.entities.Operation;
import ru.chepusov.NauJava.entities.User;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Repository
public class OperationRepositoryCriteriaImpl implements OperationRepositoryCriteria{
    private final EntityManager entityManager;

    public OperationRepositoryCriteriaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Operation> findByCategory(String name) {

        if (name == null || name.isBlank()) {
            return Collections.emptyList(); // или бросить исключение
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = criteriaBuilder.createQuery(Operation.class);
        Root<Operation> operationRoot = criteriaQuery.from(Operation.class);

        //Join<Operation, Category> categoryJoin = operationRoot.join("category");

        //Predicate categoryPredicate = criteriaBuilder.equal(criteriaBuilder.lower(categoryJoin.get("name")), name.toLowerCase(Locale.ROOT));
        Predicate categoryPredicate = criteriaBuilder.equal(criteriaBuilder.lower(operationRoot.get("category").get("name")), name.toLowerCase(Locale.ROOT));

        criteriaQuery.select(operationRoot).where(categoryPredicate);
        //cq.select(operationRoot).where(categoryPredicate); // select опционален, но делает код чище

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
