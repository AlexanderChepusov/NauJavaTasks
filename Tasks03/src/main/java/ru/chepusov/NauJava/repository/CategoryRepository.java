package ru.chepusov.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.chepusov.NauJava.entities.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByName(String name);

    List<Category> findByPercentageLessThanAndPercentageGreaterThan(int less, int more);

    default void addNewCategory(String name, int perc, String type) {
        Category result = new Category(name, perc, type);
        save(result);
    }

    @Transactional
    void deleteByNameIgnoreCase(String name);
    @Transactional
    void deleteByName(String name);
}
