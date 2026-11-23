package ru.chepusov.NauJava;

import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import ru.chepusov.NauJava.entities.Category;
import ru.chepusov.NauJava.entities.User;
import ru.chepusov.NauJava.repository.OperationRepository;
import ru.chepusov.NauJava.service.OperationAnalysisService;

import java.util.List;

@SpringBootTest
public class OpRepTest {

    private final OperationRepository opRep;
    private final OperationAnalysisService oas;

    @Autowired
    public OpRepTest(OperationRepository opRep, OperationAnalysisService oas) {
        this.opRep = opRep;
        this.oas = oas;
    }


    @Test
    public void queryTest() {
        System.out.println("----------------QUERY TEST----------------");
        int id= 13;
        String data[][] = oas.getOperationsPerCategory(id);
        for (String[] x : data) {
            for (String y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
        Assertions.assertEquals(5, data.length);
    }

    @Test
    public void getCatsTest() {
        //User user =
        List<String> list = opRep.getUserCategorises(Long.valueOf(13l));
        System.out.println(list);
        Assertions.assertEquals(5, list.size(), "Не совпало((");
    }
}



