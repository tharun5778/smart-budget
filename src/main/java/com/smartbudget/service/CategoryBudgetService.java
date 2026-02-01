package com.smartbudget.service;

import com.smartbudget.entity.Category;
import com.smartbudget.entity.CategoryBudget;
import com.smartbudget.entity.User;
import com.smartbudget.exception.BusinessException;
import com.smartbudget.repository.CategoryBudgetRepository;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
public class CategoryBudgetService {
    private final CategoryBudgetRepository repository;

    public CategoryBudgetService(CategoryBudgetRepository repository) {
        this.repository = repository;
    }

    public void setCategoryLimit(User user, Category category, double limit){

        YearMonth month = YearMonth.now();

        repository.findByUserAndCategoryAndMonth(user, category, month)
                .ifPresent(err -> {
                    throw new BusinessException("Category budget already set for this month");
                });

        CategoryBudget cb = new CategoryBudget();
        cb.setUser(user);
        cb.setCategory(category);
        cb.setLimitAmount(limit);
        cb.setMonth(month);
        cb.setUsedAmount(0);

        repository.save(cb);
    }
}
