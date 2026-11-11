package ru.chepusov.NauJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chepusov.NauJava.entities.Operation;
import ru.chepusov.NauJava.entities.Report;
import ru.chepusov.NauJava.repository.ReportRepository;
import ru.chepusov.NauJava.service.ReportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reports")
public class ReportWebController {
    private final ReportRepository repRep;
    private final ReportService repService;

    public ReportWebController(ReportRepository repRep, ReportService repService) {
        this.repRep = repRep;
        this.repService = repService;
    }

    @GetMapping("/")
    public String showReports(Model model) {
        List<Report> list = (List<Report>) repRep.findAll();
        model.addAttribute("reports", list);
        return "reports";
    }

    @GetMapping("/{id}")
    public String showReports(@PathVariable long id, Model model) {
        List<Report> list = new ArrayList<>();
        list.add(repRep.findById(id).orElse(null));
        model.addAttribute("reports", list);
        return "reports";
    }

    @GetMapping("/new")
    public String createNewReport(Model model) {
        Map.Entry<Long, List<Operation>> pair = repService.createReport();
        return "redirect:/reports/" + pair.getKey();

    }
}
