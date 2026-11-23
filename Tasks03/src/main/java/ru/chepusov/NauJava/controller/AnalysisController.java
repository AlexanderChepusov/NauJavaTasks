package ru.chepusov.NauJava.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chepusov.NauJava.entities.Category;
import ru.chepusov.NauJava.entities.Operation;
import ru.chepusov.NauJava.entities.User;
import ru.chepusov.NauJava.service.OperationAnalysisService;

import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {
    private final OperationAnalysisService oas;

    public AnalysisController(OperationAnalysisService oas) {
        this.oas = oas;
    }

    @GetMapping("/purchases")
    public String showPurchases(@AuthenticationPrincipal User user, Model model) {
        Long id = user.getId();
        String[][] data = oas.getOperationsPerCategory(id); // new TreeMap<>();
        model.addAttribute("data", data);
        return "purchaseAnalysis";
    }

    @GetMapping("/savings")
    public String showSavings() {
        return "savingAnalysis";
    }
}
