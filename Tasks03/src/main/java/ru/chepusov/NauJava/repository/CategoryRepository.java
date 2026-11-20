package ru.chepusov.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.chepusov.NauJava.entities.Category;

import java.util.List;

@RepositoryRestResource
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByName(String name);

    List<Category> findByPercentageLessThanAndPercentageGreaterThan(int less, int more);

    default void addNewCategory(String name, int perc, String type) {
        Category result = new Category(name, perc, type);
        save(result);
    }

    List<Category> findAllByOrderByName(); //findAll(Sort.by("name"))

    @Transactional
    void deleteByNameIgnoreCase(String name);
    @Transactional
    void deleteByName(String name);
}
