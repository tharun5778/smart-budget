package com.smartbudget.dto;

public class CreateBudgetRequest {
    private double totalLimit;

    public double getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(double totalLimit) {
        this.totalLimit = totalLimit;
    }
}