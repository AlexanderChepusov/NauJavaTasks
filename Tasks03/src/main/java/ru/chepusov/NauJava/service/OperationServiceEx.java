package ru.chepusov.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chepusov.NauJava.repository.OperationRep;

//@Service
public class OperationServiceEx {
  //  private OperationRep.OperationRepository ore;
    //@Autowired
//    public OperationServiceEx(OperationRep.OperationRepository ore) {
//        this.ore = ore;
//    }

    public void showCats() {
        System.out.println("Food: ");
        System.out.println();
    }
}
