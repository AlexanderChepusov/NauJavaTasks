package ru.chepusov.NauJava.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.chepusov.NauJava.entities.MoneyOperation;
import ru.chepusov.NauJava.repository.OperationRep;

import java.util.ArrayList;
import java.util.List;
@Component
public class MoneyOperationService implements OperationService<MoneyOperation>{
    private final OperationRep operationRep;

    @Value("${app.name}")
    private String appName;
    @Value("${app.version}")
    private String appVersion;

    @PostConstruct
    private void showInfo(){
        System.out.println(appName + " ver. " + appVersion);
    }

    @Autowired
    public MoneyOperationService(OperationRep operationRep) {
        this.operationRep = operationRep;
    }

    @Override
    public void payment(double val, String account, String category) {
        operationRep.create(new MoneyOperation("payment", val, account, category));
    }

    @Override
    public void income(double val, String account, String category) {
        operationRep.create(new MoneyOperation("income", val, account, category));
    }

    @Override
    public double summCategory(String category) {
        double result = 0.0;
        List<Integer> ids = operationRep.getIDs();
        for (Integer x : ids) {
            MoneyOperation obj = operationRep.read(x);
            if (obj.getCategory().equalsIgnoreCase(category)) {
                result += obj.getValue();
            }
        }
        return result;
    }

    @Override
    public double summAccount(String account) {
        double result = 0.0;
        List<Integer> ids = operationRep.getIDs();
        for (Integer x : ids) {
            MoneyOperation obj = operationRep.read(x);
            if (obj.getAccount().equalsIgnoreCase(account)) {
                //int val = obj.getValue();
                result += obj.getValue();
            }
        }
        return result;
    }

    public void showAll() {
        List<Integer> list = operationRep.getIDs();//new ArrayList<>();
        for (Integer x : list) {
            System.out.println(operationRep.read(x));
        }
    }
}
