package com.smartbudget.controller;

import com.smartbudget.dto.CreateExpenseRequest;
import com.smartbudget.entity.User;
import com.smartbudget.security.SecurityUtil;
import com.smartbudget.service.ExpenseService;
import com.smartbudget.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpenseController(ExpenseService expenseService,
                            UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBudget(@RequestBody CreateExpenseRequest request) {

        String username = SecurityUtil.getCurrentUsername();
        User user = userService.getCurrentUser(username);

        expenseService.addExpense(
                user,
                request.getAmount(),
                request.getCategory(),
                request.getDescription()
        );
    }
}
