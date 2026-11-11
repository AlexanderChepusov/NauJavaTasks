package ru.chepusov.NauJava.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chepusov.NauJava.entities.Operation;
import ru.chepusov.NauJava.repository.OperationRepository;

import java.util.List;

@Controller
@RequestMapping("/ops")
@Tag(name = "Операции")
public class OperationWebController {
    private final OperationRepository opRep;

    public OperationWebController(OperationRepository opRep) {
        this.opRep = opRep;
    }

    @GetMapping("/")
    public String showAllOperations(Model model){
        List<Operation> list = (List<Operation>) opRep.findAll();
        model.addAttribute("operations", list);
        return "operations";
    }

    @GetMapping("/{id}")
    public String showUserOperations(@PathVariable long id, Model model) {
        List<Operation> list = opRep.findByUserId(id);
        model.addAttribute("operations", list);
        return "operations";
    }

    @GetMapping
    public String showOneCateory(@RequestParam String category, Model model) {
        List<Operation> list = opRep.findByCategory_Name(category);
        model.addAttribute("operations", list);
        return "operations";
    }

}
