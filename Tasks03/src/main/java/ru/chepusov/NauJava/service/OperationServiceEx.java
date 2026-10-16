package ru.chepusov.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chepusov.NauJava.repository.OpRepExample;

@Service
public class OperationServiceEx {
    private OpRepExample ore;
    @Autowired
    public OperationServiceEx(OpRepExample ore) {
        this.ore = ore;
    }
}
