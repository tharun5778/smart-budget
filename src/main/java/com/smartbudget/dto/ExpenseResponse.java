package com.smartbudget.dto;

import com.smartbudget.entity.Category;
import java.time.LocalDate;


public class ExpenseResponse {

    private double amount;
    private Category category;
    private LocalDate date;
    private String description;

    public ExpenseResponse(double amount,
                           Category category,
                           LocalDate date,
                           String description) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    // getters

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
