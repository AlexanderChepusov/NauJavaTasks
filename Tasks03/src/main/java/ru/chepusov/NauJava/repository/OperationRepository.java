package ru.chepusov.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chepusov.NauJava.entities.Operation;

import java.util.List;

public interface OperationRepository extends CrudRepository<Operation, Long> {
    List<Operation> findByCategory(String category);
}
