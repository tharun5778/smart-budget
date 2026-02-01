package com.smartbudget.controller;

import com.smartbudget.dto.CategoryBudgetRequest;
import com.smartbudget.entity.User;
import com.smartbudget.security.SecurityUtil;
import com.smartbudget.service.CategoryBudgetService;
import com.smartbudget.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category-budgets")
public class CategoryBudgetController {
    private final UserService userService;
    private final CategoryBudgetService categoryBudgetService;

    public CategoryBudgetController(UserService userService, CategoryBudgetService categoryBudgetService){
        this.userService = userService;
        this.categoryBudgetService = categoryBudgetService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void setCategoryBudget(@RequestBody CategoryBudgetRequest request){
        String username = SecurityUtil.getCurrentUsername();
        User user = userService.getCurrentUser(username);

        categoryBudgetService.setCategoryLimit(
                user,
                request.getCategory(),
                request.getLimit()
        );
    }
}
