package ru.chepusov.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.chepusov.NauJava.entities.Operation;

import java.util.List;
import java.util.Locale;

public interface OperationRepository extends CrudRepository<Operation, Long> {

    List<Operation> findByCategory_Name(String category);

    default List<Operation> findByCategoryName(String category) {
        if (category != null || !category.isEmpty()) {
            category =  category.substring(0, 1).toUpperCase() + category.substring(1);
        }
        System.out.println("Search in category " + category);
        return findByCategory_NameIgnoreCase(category);
    }
    List<Operation> findByType(String type);

    List<Operation> findByAmountGreaterThan(double price);
    List<Operation> findByAmountLessThan(double price);

    List<Operation> findByCategory_NameIgnoreCase(String name);

    @Query("SELECT o FROM Operation o join o.category c where c.name = :cat ")
    List<Operation> findByCat(@Param("cat") String cat);
}

