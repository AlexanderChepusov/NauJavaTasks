package ru.chepusov.NauJava.repository;

import ru.chepusov.NauJava.entities.Operation;

import java.util.List;

public interface OperationRepositoryCustom {
    /**Находит операции с выбранной категорией
     *
     * @param name имя категории
     * @return список операций с выбранной категорией
     */
    List<Operation> findByCategory(String name);
    List<Operation> findOperationsByCategoryName(String name);
}
