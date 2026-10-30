package ru.chepusov.NauJava.entities;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String type;

    @Column
    private double amount;

    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category category;

    @ManyToOne
    private Account account;

    @ManyToOne
    private User user;

    @Column
    private OffsetDateTime dateTime;

    @ManyToOne
    private Marketplace marketplace;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    @Override
    public String toString() {
        return (type.equalsIgnoreCase("payment") ? "PAYMENT " : "INCOME ") + "$" + amount + " in "
                + category + " category " +
                dateTime + " in "
                + marketplace + " (userID "
                + user + "; accountID "
                + account + ").";
//                "type='" + type + '\'' +
//                ", amount=" + amount +
//                ", category=" + category +
//                ", account=" + account +
//                ", user=" + user +
//                ", dateTime=" + dateTime +
//                ", marketplace=" + marketplace +
//                '}';
    }
}
