package com.example.JWTAuthorization.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private long balance;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "users_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "username"))
    private final Set<User> users = new HashSet<>();

    public Group(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }

    public Group() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
