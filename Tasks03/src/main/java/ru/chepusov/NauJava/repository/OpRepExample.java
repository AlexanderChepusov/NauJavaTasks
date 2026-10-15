package ru.chepusov.NauJava.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.chepusov.NauJava.entities.Operation;

import java.util.List;

public class OpRepExample {

    private OperationRepository operationRepository;

    @Autowired
    public OpRepExample(OperationRepository or) {
        this.operationRepository = or;
    }

    public static void main(String[] args) {
        List<Operation> list = this.operationRepository.findAll();
    }
}
