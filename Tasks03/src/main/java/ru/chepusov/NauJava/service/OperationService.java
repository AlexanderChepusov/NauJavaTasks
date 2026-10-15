package ru.chepusov.NauJava.service;

import java.util.List;

public interface OperationService<T> {
    void payment(double value, String account, String category);
    void income(double value, String account, String category);
    //List<T> showCategory(String category);
    //List<T> showAccounts(String category);
    double summCategory(String category);
    double summAccount(String account);
}
