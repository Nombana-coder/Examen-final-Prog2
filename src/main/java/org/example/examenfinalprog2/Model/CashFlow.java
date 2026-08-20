package com.example.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public abstract class CashFlow {

    private String id;
    private Instant createdAt;
    private BigDecimal amount;

    protected CashFlow() {
    }

    protected CashFlow(String id, Instant createdAt, BigDecimal amount) {
        this.id = id;
        this.createdAt = createdAt;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashFlow cashFlow = (CashFlow) o;
        return Objects.equals(id, cashFlow.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CashFlow{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", amount=" + amount +
                '}';
    }
}
