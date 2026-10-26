package ru.chepusov.NauJava.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ru.chepusov.NauJava.entities.Category;
import ru.chepusov.NauJava.entities.Operation;
import ru.chepusov.NauJava.repository.OperationRepositoryCustom;

import java.util.List;

@Repository
public class OperationRepositoryImpl implements OperationRepositoryCustom {
    private final EntityManager entityManager;

    public OperationRepositoryImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public List<Operation> findByCategory(String name) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = criteriaBuilder.createQuery(Operation.class);

        Root<Operation> userRoot = criteriaQuery.from(Operation.class);
        Predicate namePredicate = criteriaBuilder.equal(userRoot.get("name"), name);

        criteriaQuery.select(userRoot).where(namePredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
        //return null;
    }

    public List<Operation> findOperationsByCategoryName(String categoryName) {
        // 1. Получаем CriteriaBuilder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // 2. Создаем запрос, который вернет Operation
        CriteriaQuery<Operation> query = cb.createQuery(Operation.class);

        // 3. Определяем корень запроса (FROM operations)
        Root<Operation> operationRoot = query.from(Operation.class);

        // 4. Делаем JOIN с таблицей Category (операция -> категория)
        // Указываем тип JoinType.INNER (по умолчанию), чтобы найти только те операции, у которых есть категория
        Join<Operation, Category> categoryJoin = operationRoot.join("category", JoinType.INNER);

        // 5. Строим условие WHERE
        // Если нужен поиск БЕЗ учета регистра (как в IgnoreCase):
        Expression<String> categoryNameExpr = cb.lower(categoryJoin.get("name"));
        Predicate predicate = cb.equal(categoryNameExpr, categoryName.toLowerCase());

        // Если нужен поиск С учетом регистра (просто equals), то было бы так:
        // Predicate predicate = cb.equal(categoryJoin.get("name"), categoryName);

        // 6. Применяем условие к запросу и выбираем корень (SELECT operation FROM ...)
        query.select(operationRoot).where(predicate);

        // 7. Выполняем запрос
        return entityManager.createQuery(query).getResultList();
    }
}
