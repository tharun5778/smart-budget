package com.smartbudget.service;

import com.smartbudget.entity.*;
import com.smartbudget.repository.BudgetRepository;
import com.smartbudget.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          BudgetRepository budgetRepository) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
    }

    public void addExpense(User user, double amount, Category category, String description) {

        String currentMonth = YearMonth.now().toString();

        Budget budget = budgetRepository.findByUserAndMonth(user, currentMonth)
                .orElseThrow(() -> new RuntimeException("No budget set for this month"));

        if (budget.getUsedAmount() + amount > budget.getTotalLimit()) {
            throw new RuntimeException("Budget limit exceeded");
        }

        Expense expense = new Expense();
        expense.setUser(user);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDate(LocalDate.now());
        expense.setDescription(description);

        budget.setUsedAmount(budget.getUsedAmount() + amount);

        expenseRepository.save(expense);
        budgetRepository.save(budget);
    }
}
