package com.smartbudget.repository;

import com.smartbudget.entity.Expense;
import com.smartbudget.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);
}
