package com.smartbudget.repository;

import com.smartbudget.entity.Category;
import com.smartbudget.entity.CategoryBudget;
import com.smartbudget.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.Optional;

public interface CategoryBudgetRepository
        extends JpaRepository<CategoryBudget, Long> {

    Optional<CategoryBudget> findByUserAndCategoryAndMonth(
            User user,
            Category category,
            YearMonth month
    );
}
