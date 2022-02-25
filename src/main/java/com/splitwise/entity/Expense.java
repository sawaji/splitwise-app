package com.splitwise.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="payer_id")
    private User payer;
    private BigDecimal amount;
    private Date date;
    private String description;


    public Expense(){}

    public Expense(User payer, BigDecimal amount, Date date, String description) {
        this.payer = payer;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public User getPayer() {
        return payer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public void setPayer(User payer){
        this.payer = payer;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder {
        private User payer;
        private BigDecimal amount = BigDecimal.ZERO;
        private Date date;
        private String description;

        public Builder(){}

        public Builder withPayer(User payer){
            this.payer = payer;
            return this;
        }

        public Builder withAmount(BigDecimal amount){
            this.amount = amount;
            return this;
        }

        public Builder withDate(Date date){
            this.date = date;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Expense build(){
            return new Expense(payer, amount, date, description);
        }
    }
}