package org.example.examenfinalprog2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CashFlow {

    private String id;
    private Instant createdAt;
    private BigDecimal amount;

    // Foreign key toward User: reflects the "make" 1..1 -> 0..* link on the diagram
    private String userId;
}
