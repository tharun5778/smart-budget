package com.smartbudget.repository;

import com.smartbudget.entity.Budget;
import com.smartbudget.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Optional<Budget> findByUserAndMonth(User user, YearMonth month);
}
