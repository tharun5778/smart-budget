package com.smartbudget.repository;

import com.smartbudget.entity.Expense;
import com.smartbudget.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserAndDateBetween(
            User user,
            LocalDate start,
            LocalDate end
    );
}
