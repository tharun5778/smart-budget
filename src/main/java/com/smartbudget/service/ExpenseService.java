package com.smartbudget.service;

import com.smartbudget.dto.ExpenseResponse;
import com.smartbudget.dto.MonthlySummaryResponse;
import com.smartbudget.entity.*;
import com.smartbudget.exception.BusinessException;
import com.smartbudget.repository.BudgetRepository;
import com.smartbudget.repository.ExpenseRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Transactional
    public void addExpense(User user, double amount, Category category, String description) {

        String currentMonth = YearMonth.now().toString();

        Budget budget = budgetRepository.findByUserAndMonth(user, currentMonth)
                .orElseThrow(() -> new BusinessException("No budget set for this month"));

        if (budget.getUsedAmount() + amount > budget.getTotalLimit()) {
            throw new BusinessException("Budget limit exceeded");
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

    @Transactional(readOnly = true)
    public List<ExpenseResponse> getMonthlyExpenses(User user, YearMonth month) {

        LocalDate start = month.atDay(1);
        LocalDate end = month.atEndOfMonth();

        return expenseRepository
                .findByUserAndDateBetween(user, start, end)
                .stream()
                .map(expense ->
                        new ExpenseResponse(
                                expense.getAmount(),
                                expense.getCategory(),
                                expense.getDate(),
                                expense.getDescription()
                        )
                )
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MonthlySummaryResponse getMonthlySummary(User user, YearMonth month){

        Budget budget = budgetRepository
                .findByUserAndMonth(user, month.toString())
                .orElseThrow(() ->
                        new BusinessException("No budget set for this month"));

        List<Expense> expenses = expenseRepository.findByUserAndDateBetween(
                user,
                month.atDay(1),
                month.atEndOfMonth());

        double totalSpent = expenses.stream().mapToDouble(Expense::getAmount).sum();
        Map<Category, Double> categoryWiseSpending = new HashMap<>();

        for(Expense  expense : expenses){
            Category category = expense.getCategory();
            double amount = expense.getAmount();

            categoryWiseSpending.put(
                    category,
                    categoryWiseSpending.getOrDefault(category, 0.0) + amount
            );
        }

        return new MonthlySummaryResponse(
                budget.getTotalLimit(),
                totalSpent,
                budget.getTotalLimit() - totalSpent,
                categoryWiseSpending
        );
    }
}
