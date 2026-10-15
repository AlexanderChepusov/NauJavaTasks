package ru.chepusov.NauJava.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MoneyOperation {
    private int id;         //номер операциии
    private String type;    //тип операции: пополнение или расход - возможен enum
    private double value;   //сумма операции
    private String account; //счет пополнение или списания
    private String category;    //категория покупки или операции
    private String comment;     //пояснение к операции

    public MoneyOperation(int id) {
        this.id = id;
    }
//@Autowired
    public MoneyOperation(String type, double value, String account, String category) {
        this.type = type;
        this.value = value;
        this.account = account;
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "(" + id +
                ") " + (type.equalsIgnoreCase("payment") ? "PAYMENT" : "INCOME") + //'\'' +
                " $" + value +
                ": account " + account + //'\'' +
                " in category " + category + '\'' +
                ". Comment: " + comment;// + '\'' +
    }
}
