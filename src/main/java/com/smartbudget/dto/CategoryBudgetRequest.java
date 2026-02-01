package com.smartbudget.dto;

import com.smartbudget.entity.Category;

public class CategoryBudgetRequest {
    private Category category;
    private double limit;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
