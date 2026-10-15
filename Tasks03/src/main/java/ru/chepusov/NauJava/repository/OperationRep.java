package ru.chepusov.NauJava.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.chepusov.NauJava.config.Config;
import ru.chepusov.NauJava.entities.MoneyOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class OperationRep implements CRUDRep<MoneyOperation>{
    private static int count = 0;
    private final Map<Integer, MoneyOperation> DB;
@Autowired
    public OperationRep(Map<Integer, MoneyOperation> map) {
        this.DB = map;
    }

    @PostConstruct
    private void fillMap(){
        this.create(new MoneyOperation("payment", 123.1, "bank1", "Cat1"));
        this.create(new MoneyOperation("payment", 223.1, "bank1", "Cat2"));
        this.create(new MoneyOperation("payment", 323.1, "bank1", "Cat3"));
        this.create(new MoneyOperation("get", 423.1, "bank1", "Cat2"));
        this.create(new MoneyOperation("payment", 523.1, "bank2", "Cat1"));
        this.create(new MoneyOperation("payment", 623.1, "bank2", "Cat2"));
        this.create(new MoneyOperation("payment", 723.1, "bank2", "Cat3"));
        this.create(new MoneyOperation("get", 823.1, "bank2", "Cat1"));
    }


    @Override
    public void create(MoneyOperation entity) {
        entity.setId(count);
        DB.put(count++, entity);
    }

    @Override
    public MoneyOperation read(int id) {
        return DB.get(id);
    }


    @Override
    public void update(int id, MoneyOperation entity) {
        DB.put(id, entity);
    }

    @Override
    public void delete(int id) {
        DB.remove(id);
    }

    public List<Integer> getIDs() {
        List<Integer> result = new ArrayList<>();
        for (Integer x : DB.keySet()) {
            result.add(Integer.valueOf(x));
        }
        return result;
    }
}
