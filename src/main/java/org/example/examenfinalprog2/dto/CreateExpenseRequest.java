package org.example.examenfinalprog2.dto;

import org.example.examenfinalprog2.model.ExpenseFrequency;

import java.math.BigDecimal;

public record CreateExpenseRequest(
        String userId,
        BigDecimal amount,
        String reason,
        ExpenseFrequency frequency
) {
}
