package ru.chepusov.NauJava.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.chepusov.NauJava.entities.Operation;
import ru.chepusov.NauJava.entities.User;
import ru.chepusov.NauJava.repository.OperationRepository;
import ru.chepusov.NauJava.service.DataPrepareService;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.List;

@Controller
@RequestMapping("/operations")
@Tag(name = "Операции")
public class OperationWebController {
    private final OperationRepository opRep;
    private final DataPrepareService dps;

    public OperationWebController(OperationRepository opRep, DataPrepareService dps) {
        this.opRep = opRep;
        this.dps = dps;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public String showAllOperations(Model model){
        List<Operation> list = (List<Operation>) opRep.findAll();
        model.addAttribute("operations", list);
        return "operations";
    }

    @GetMapping("/{id}")
    public String showUserOperations(@PathVariable long id, Model model, @AuthenticationPrincipal User user) {
        if (id != user.getId()) {

            throw new AccessDeniedException("Вам недуступна данная операция");
            }

//        CompletableFuture<List<Category>> future1 = CompletableFuture.supplyAsync(() -> {
//            // Симулируем длительную задачу
//            long startTime1 = System.currentTimeMillis();
//            usersCount.set((int) userRep.count());
//            time1.set(System.currentTimeMillis() - startTime1);
//            return usersCount;
//        });

        model.addAttribute("catList", dps.prepareCategories());
        model.addAttribute("accList", user.getAccounts());
        model.addAttribute("mpList", dps.prepareMarketplaces());


        List<Operation> list = opRep.findByUserId(id);
        model.addAttribute("operations", list);
        model.addAttribute("operation", new Operation());
        return "operationsList";
    }

    @GetMapping("/cat/{category}")
    public String showOneCateory(@AuthenticationPrincipal User user,@PathVariable String category, Model model) {

        model.addAttribute("catList", dps.prepareCategories());
        model.addAttribute("accList", user.getAccounts());
        model.addAttribute("mpList", dps.prepareMarketplaces());


        List<Operation> list = opRep.findByCategory_Name(category);
        model.addAttribute("operations", list);
        model.addAttribute("operation", new Operation());
        return "operationsList";
    }

    @PostMapping("/new")
    public String addNewOperation(@AuthenticationPrincipal User user, Model model
            , @ModelAttribute Operation operation, @RequestParam String caty,
                                  @RequestParam String dt) {
        OffsetDateTime odt;
        if (dt.isEmpty()) {
            odt = OffsetDateTime.now();
        } else {
            LocalDateTime dateTime = LocalDateTime.parse(dt);
            ZoneOffset offset = ZoneOffset.ofHours(5);
            odt = OffsetDateTime.of(dateTime, offset);
        }

        operation.setDateTime(odt);
        operation.setUser(user);
        operation.setCategory(dps.getCategoryByName(caty));
        System.out.println(operation);
        opRep.save(operation);

        model.addAttribute("catList", dps.prepareCategories());
        model.addAttribute("accList", user.getAccounts()); //dps.getUserAccounts(user.getId()));
        model.addAttribute("mpList", dps.prepareMarketplaces());

        List<Operation> list = opRep.findByUserId(user.getId());
        model.addAttribute("operation", new Operation());
        model.addAttribute("operations", list);
        return "redirect:/operations/" + user.getId();
    }

}
