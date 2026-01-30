package com.smartbudget.dto;

import com.smartbudget.entity.Category;

import java.util.Map;

public class MonthlySummaryResponse {
    private double totalBudget;
    private double totalSpent;
    private  double remaining;
    private Map<Category, Double> categoryWiseSpending;

    public MonthlySummaryResponse(double totalBudget, double totalSpent, double remaining, Map<Category, Double> categoryWiseSpending){
        this.totalBudget = totalBudget;
        this.totalSpent = totalSpent;
        this.remaining = remaining;
        this.categoryWiseSpending = categoryWiseSpending;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public double getRemaining() {
        return remaining;
    }

    public Map<Category, Double> getCategoryWiseSpending() {
        return categoryWiseSpending;
    }
}
