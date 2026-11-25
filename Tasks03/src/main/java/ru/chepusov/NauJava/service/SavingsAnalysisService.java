package ru.chepusov.NauJava.service;

import org.springframework.stereotype.Service;
import ru.chepusov.NauJava.repository.AccountRepository;
import ru.chepusov.NauJava.repository.OperationRepository;
import ru.chepusov.NauJava.repository.UserRepository;

import java.util.List;

@Service
public class SavingsAnalysisService {
    private final UserRepository userRep;
    private final OperationRepository opRep;
    private final AccountRepository accRep;
    private final DataPrepareService dps;

    public SavingsAnalysisService(UserRepository userRep, OperationRepository opRep, AccountRepository accRep, DataPrepareService dps) {
        this.userRep = userRep;
        this.opRep = opRep;
        this.accRep = accRep;
        this.dps = dps;
    }

    public List<String> getUserCategories(long id) {
        return opRep.getUserSavingCategorises(id);
    }
}
