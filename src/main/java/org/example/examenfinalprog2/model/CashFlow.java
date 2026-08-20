package org.example.examenfinalprog2.model;

import java.math.BigDecimal;
import java.time.Instant;

public sealed interface CashFlow permits Donation, Expense {

    String id();

    Instant createdAt();

    BigDecimal amount();

    String userId();
}
