package com.smartbudget.service;

import com.smartbudget.entity.Budget;
import com.smartbudget.entity.User;
import com.smartbudget.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Budget createMonthlyBudget(User user, double limit) {

        YearMonth currentMonth = YearMonth.now();

        budgetRepository.findByUserAndMonth(user, currentMonth)
                .ifPresent(b -> {
                    throw new RuntimeException("Budget already exists for this month");
                });

        Budget budget = new Budget();
        budget.setUser(user);
        budget.setMonth(currentMonth);
        budget.setTotalLimit(limit);
        budget.setUsedAmount(0);

        return budgetRepository.save(budget);
    }
}
