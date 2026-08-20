package org.example.examenfinalprog2.repository;

import org.example.examenfinalprog2.model.Expense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ExpenseRowMapper rowMapper = new ExpenseRowMapper();

    public ExpenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Expense> findAll() {
        return jdbcTemplate.query("SELECT * FROM expense", rowMapper);
    }

    public List<Expense> findByUserId(String userId) {
        return jdbcTemplate.query(
                "SELECT * FROM expense WHERE user_id = ?",
                rowMapper,
                userId
        );
    }

    public void save(Expense expense) {
        jdbcTemplate.update(
                "INSERT INTO expense (id, created_at, amount, user_id, reason, frequency) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                expense.id(),
                java.sql.Timestamp.from(expense.createdAt()),
                expense.amount(),
                expense.userId(),
                expense.reason(),
                expense.frequency().name()
        );
    }
}
