package org.example.examenfinalprog2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Expense extends CashFlow {

    private String reason;
    private ExpenseFrequency frequency;

    public Expense(String id, Instant createdAt, BigDecimal amount, String userId,
                    String reason, ExpenseFrequency frequency) {
        super(id, createdAt, amount, userId);
        this.reason = reason;
        this.frequency = frequency;
    }
}
