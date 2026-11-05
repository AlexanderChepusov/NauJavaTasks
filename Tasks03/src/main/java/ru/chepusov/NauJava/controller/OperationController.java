package ru.chepusov.NauJava.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chepusov.NauJava.entities.Operation;
import ru.chepusov.NauJava.repository.OperationRepository;

import java.util.List;

@RestController
@RequestMapping("/ops")
@Tag(name = "Операции")
public class OperationController {
    private final OperationRepository opRep;

    public OperationController(OperationRepository opRep) {
        this.opRep = opRep;
    }

    @GetMapping("/{money}")
    public List<Operation> getExpencive(@PathVariable double money) {
        return opRep.findByAmountGreaterThan(money);
    }
}
