package com.smartbudget.event;

import com.smartbudget.entity.CategoryBudget;

public class ExpenseAddedEvent {

    private final CategoryBudget categoryBudget;

    public
    ExpenseAddedEvent(CategoryBudget categoryBudget) {
        this.categoryBudget = categoryBudget;
    }

    public CategoryBudget getCategoryBudget() {
        return categoryBudget;
    }
}
