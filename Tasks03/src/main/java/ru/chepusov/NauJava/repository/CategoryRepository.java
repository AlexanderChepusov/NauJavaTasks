package ru.chepusov.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chepusov.NauJava.entities.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByName(String name);

    List<Category> findByPercentageLessThanAndPercentageGreaterThan(int less, int more);
}
