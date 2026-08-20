package org.example.examenfinalprog2.services;

import org.example.examenfinalprog2.dto.CreateExpenseRequest;
import org.example.examenfinalprog2.model.CashFlow;
import org.example.examenfinalprog2.model.Expense;
import org.example.examenfinalprog2.model.ExpenseFrequency;
import org.example.examenfinalprog2.repository.DonationRepository;
import org.example.examenfinalprog2.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class CashFlowService {

    private final DonationRepository donationRepository;
    private final ExpenseRepository expenseRepository;

    public CashFlowService(DonationRepository donationRepository, ExpenseRepository expenseRepository) {
        this.donationRepository = donationRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<CashFlow> findByType(String type) {
        if (type == null || type.isBlank()) {
            return concat(donationRepository.findAll(), expenseRepository.findAll());
        }

        return switch (type.toLowerCase()) {
            case "donation" -> List.copyOf(donationRepository.findAll());
            case "expense" -> List.copyOf(expenseRepository.findAll());
            default -> throw new IllegalArgumentException(
                    "Unknown cash-flow type: '" + type + "' (expected 'donation' or 'expense')"
            );
        };
    }

    /**
     * Same idea as findByType, but scoped to a single user: merges that
     * user's donations and expenses (each repository already filters by
     * user_id at the SQL level, so no in-memory filtering needed here).
     */
    public List<CashFlow> findByUserId(String userId) {
        return concat(
                donationRepository.findByUserId(userId),
                expenseRepository.findByUserId(userId)
        );
    }

    /**
     * Generates id/createdAt server-side, defaults a missing frequency to
     * NONE, then persists via ExpenseRepository. Returns the created
     * Expense so the controller can echo it back (e.g. with 201 Created).
     */
    public Expense createExpense(CreateExpenseRequest request) {
        Expense expense = new Expense(
                UUID.randomUUID().toString(),
                Instant.now(),
                request.amount(),
                request.userId(),
                request.reason(),
                request.frequency() == null ? ExpenseFrequency.NONE : request.frequency()
        );
        expenseRepository.save(expense);
        return expense;
    }

    /**
     * Global balance = total donations - total expenses.
     */
    public BigDecimal getBalance() {
        BigDecimal totalDonations = donationRepository.findAll().stream()
                .map(d -> d.amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = expenseRepository.findAll().stream()
                .map(e -> e.amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalDonations.subtract(totalExpenses);
    }

    private List<CashFlow> concat(List<? extends CashFlow> a, List<? extends CashFlow> b) {
        return java.util.stream.Stream.concat(a.stream(), b.stream()).toList();
    }
}
