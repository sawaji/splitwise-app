package com.splitwise.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    private String username;

    private String name;
    private String lastname;
    private BigDecimal balance;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="expense_id")
    @JsonBackReference
    private List<Expense> expenses;

    public User() {
        this.balance = BigDecimal.ZERO;
    }

    public User(String username, String name, String lastname,List<Expense> expenses1) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.balance = BigDecimal.ZERO;
        this.expenses=expenses1;
    }

    public String getUsername() {
        return username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) && lastname.equals(user.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname);
    }

    public static class Builder{
        private String username;
        private String name;
        private String lastname;
        private List<Expense> expenseList;

        public Builder(){}

        public User.Builder withUsername(String username){
            this.username = username;
            return this;
        }

        public User.Builder withName(String name){
            this.name = name;
            return this;
        }

        public User.Builder withLastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public User.Builder withExpenseList(List<Expense> expenses){
            this.expenseList = expenses;
            return this;
        }

        public User build(){
            return new User(username, name, lastname, expenseList);
        }
    }
}