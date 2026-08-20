package org.example.examenfinalprog2.model;

import java.math.BigDecimal;
import java.time.Instant;

public record Expense(
        String id,
        Instant createdAt,
        BigDecimal amount,
        String userId,
        String reason,
        ExpenseFrequency frequency
) implements CashFlow {
}
