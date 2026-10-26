package ru.chepusov.NauJava.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "marketplaces")

public class Marketplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Supermarket " +
                name +
                ", address: " + address +
                ", works until " + workingTime;
    }

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private int workingTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }
}
