package ru.chepusov.NauJava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.chepusov.NauJava.service.SavingsAnalysisService;

import java.util.List;

@SpringBootTest
public class SavingsAnalTest {
    private final SavingsAnalysisService sas;
    @Autowired
    public SavingsAnalTest(SavingsAnalysisService sas) {
        this.sas = sas;
    }

    @Test
    public void getCatsTest() {
        int id = 13;
        List<String> list = sas.getUserCategories(13);
        System.out.println(list);
    }
}
