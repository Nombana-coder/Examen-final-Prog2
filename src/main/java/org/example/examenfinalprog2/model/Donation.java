package org.example.examenfinalprog2.model;

import java.math.BigDecimal;
import java.time.Instant;

public record Donation(
        String id,
        Instant createdAt,
        BigDecimal amount,
        String userId,
        String comment
) implements CashFlow {
}
