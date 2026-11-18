package ru.chepusov.NauJava.service;

import org.springframework.stereotype.Service;
import ru.chepusov.NauJava.entities.Account;
import ru.chepusov.NauJava.entities.Category;
import ru.chepusov.NauJava.entities.Marketplace;
import ru.chepusov.NauJava.repository.AccountRepository;
import ru.chepusov.NauJava.repository.CategoryRepository;
import ru.chepusov.NauJava.repository.MarketplaceRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DataPrepareService {
    private final CategoryRepository catRep;
    private final MarketplaceRepository mpRep;
    private final AccountRepository accRep;

    public DataPrepareService(CategoryRepository catRep, MarketplaceRepository mpRep, AccountRepository accRep) {
        this.catRep = catRep;
        this.mpRep = mpRep;
        this.accRep = accRep;
    }

    public List<Category> prepareCategories() {
        return StreamSupport.stream(catRep.findAll().spliterator(), false)
                .toList();
    }
    public List<Marketplace> prepareMarketplaces() {
        return StreamSupport.stream(mpRep.findAll().spliterator(), false).toList();
    }
        public List<Account> getUserAccounts(Long id) {
            System.out.println("Собираем счета по ID)))");
            System.out.println("ID = " + id);
            List<Account> list = accRep.findByUserId(id); //.orElse(Collections.emptyList());
            System.out.println("Found: " + list.size());
        return list;
    }

    public Category getCategoryByName(String name) {
        return catRep.findByName(name);
    }

}
