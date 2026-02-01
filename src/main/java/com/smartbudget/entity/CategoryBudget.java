package com.smartbudget.entity;

import jakarta.persistence.*;

import java.time.YearMonth;

@Entity
@Table(name = "category_budgets",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "category", "month"})
        })
public class CategoryBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private double limitAmount;

    private double usedAmount;

    @Column(name = "budget_month")
    private YearMonth month;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // getters & setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public double getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(double usedAmount) {
        this.usedAmount = usedAmount;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
