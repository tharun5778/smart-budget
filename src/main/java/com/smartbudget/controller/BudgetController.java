package com.smartbudget.controller;

import com.smartbudget.dto.CreateBudgetRequest;
import com.smartbudget.entity.User;
import com.smartbudget.security.SecurityUtil;
import com.smartbudget.service.BudgetService;
import com.smartbudget.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetService budgetService;
    private final UserService userService;

    public BudgetController(BudgetService budgetService,
                            UserService userService) {
        this.budgetService = budgetService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBudget(@RequestBody CreateBudgetRequest request) {

        String username = SecurityUtil.getCurrentUsername();
        User user = userService.getCurrentUser(username);

        budgetService.createMonthlyBudget(user, request.getTotalLimit());
    }
}
