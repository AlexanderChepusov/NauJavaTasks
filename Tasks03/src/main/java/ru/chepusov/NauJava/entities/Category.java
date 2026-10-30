package ru.chepusov.NauJava.entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private String name;

    @Column
    private Integer percentage;

    @Column
    private String type;


    //private Integer id;

    public Category(String name, Integer percentage, String type) {
        this.name = name.toLowerCase(Locale.ROOT);
        this.percentage = percentage;
        if (type.equalsIgnoreCase("income"))
            this.type = type;
        else
            this.type = "payment";
    }

    public Category(String name, Integer percentage) {
        this.name = name.toLowerCase(Locale.ROOT);
        this.percentage = percentage;
        this.type = "payment";
    }

    public Category(String name) {
        this.name = name.toLowerCase(Locale.ROOT);
        this.percentage = 0;
        this.type = "payment";
    }

    @Autowired
    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Category: " +
                 name;
    }
}
