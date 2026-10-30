package ru.chepusov.NauJava.criteriaapi;

import ru.chepusov.NauJava.entities.Operation;

import java.util.List;

public interface OperationRepositoryCriteria {
    List<Operation> findByCategory(String name);
}
