package com.smartbudget.listener;

import com.smartbudget.entity.CategoryBudget;
import com.smartbudget.event.ExpenseAddedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CategoryAlertListener {

    private static final double ALERT_THRESHOLD = 0.8;

    @Async
    @EventListener
    public void handleExpenseAdded(ExpenseAddedEvent event) {

        CategoryBudget cb = event.getCategoryBudget();

        double usageRatio =
                cb.getUsedAmount() / cb.getLimitAmount();

        if (usageRatio >= ALERT_THRESHOLD) {
            // Simulated alert (email/SMS/log)
            System.out.println(
                    "⚠️ ALERT: " + cb.getCategory()
                            + " category crossed 80% usage ("
                            + (int)(usageRatio * 100) + "%)"
            );
        }
    }
}
