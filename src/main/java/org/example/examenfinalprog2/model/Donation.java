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
public class Donation extends CashFlow {

    private String comment;

    public Donation(String id, Instant createdAt, BigDecimal amount, String userId, String comment) {
        super(id, createdAt, amount, userId);
        this.comment = comment;
    }
}
