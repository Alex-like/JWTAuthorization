package com.example.JWTAuthorization.models;

import javax.persistence.*;

@Entity
@Table(name = "debts")
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long amount;

    @ManyToOne
    @JoinTable(name = "user_debts")
    private User user;

    public Debt(long amount, User user) {
        this.amount = amount;
        this.user = user;
    }

    public Debt() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User oweTo) {
        this.user = oweTo;
    }
}
