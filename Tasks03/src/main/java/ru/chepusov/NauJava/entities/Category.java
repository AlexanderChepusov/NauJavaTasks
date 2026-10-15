package ru.chepusov.NauJava.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private String name;

    @Column
    private int percentage;

    @Column
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
