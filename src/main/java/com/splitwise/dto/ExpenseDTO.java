package com.splitwise.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class ExpenseDTO {

    private String username;
    private BigDecimal amount;
    private Date date;
    private String description;

    public ExpenseDTO() {
    }

    public ExpenseDTO(String username, BigDecimal amount, Date date, String description) {
        this.username = username;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(Date dateEpoch) {
        this.date = dateEpoch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}