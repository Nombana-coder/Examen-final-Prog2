package com.example.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Donation extends CashFlow {

    private String comment;

    public Donation() {
        super();
    }

    public Donation(String id, Instant createdAt, BigDecimal amount, String comment) {
        super(id, createdAt, amount);
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id='" + getId() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", amount=" + getAmount() +
                ", comment='" + comment + '\'' +
                '}';
    }
}
