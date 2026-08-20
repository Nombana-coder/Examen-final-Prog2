package com.example.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Expense extends CashFlow {

    private String reason;
    private ExpenseFrequency frequency;

    public Expense() {
        super();
    }

    public Expense(String id, Instant createdAt, BigDecimal amount,
                    String reason, ExpenseFrequency frequency) {
        super(id, createdAt, amount);
        this.reason = reason;
        this.frequency = frequency;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ExpenseFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(ExpenseFrequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id='" + getId() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", amount=" + getAmount() +
                ", reason='" + reason + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
