package ru.chepusov.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.chepusov.NauJava.entities.Operation;

import java.util.List;
import java.util.Locale;

@RepositoryRestResource
public interface OperationRepository extends CrudRepository<Operation, Long> {

    default List<Operation> findByCategory_Name(String category) {
        return findByCategory_NameIgnoreCase(category);
    }
    List<Operation> findByCategory_NameIgnoreCase(String name);

    List<Operation> findByUserAndCategory_NameIgnoreCase(long userId, String category);

    List<Operation> findByUserAndCategory_NameIgnoreCaseAndType(long userId, String category, String type);

    List<Operation> findByUserId(long id);

    List<Operation> findByType(String type);

    @Query("SELECT o FROM Operation o join o.category c where c.name = :cat ")
    List<Operation> findByCat(@Param("cat") String cat);




    /* Пробные методы для задания к лекции 4 по ДБ
    List<Operation> findByAmountGreaterThan(double price);
    List<Operation> findByAmountLessThan(double price);

    //    @Query("SELECT o FROM Operation  where o.category.name = :cat ")
    //    List<Operation> findByCat(@Param("cat") String cat);
*/
    //Попытка победить Hibernate... неудачная
    default List<Operation> findByCategoryName(String category) {
        if (category != null || !category.isEmpty()) {
            category =  category.substring(0, 1).toUpperCase() + category.substring(1);
        }
        //System.out.println("Search in category " + category);
        return findByCategory_NameIgnoreCase(category);
    }




}

