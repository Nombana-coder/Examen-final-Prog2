package org.example.examenfinalprog2.repository;

import org.example.examenfinalprog2.model.Expense;
import org.example.examenfinalprog2.model.ExpenseFrequency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseRowMapper implements RowMapper<Expense> {

    @Override
    public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Expense(
                rs.getString("id"),
                rs.getTimestamp("created_at").toInstant(),
                rs.getBigDecimal("amount"),
                rs.getString("user_id"),
                rs.getString("reason"),
                ExpenseFrequency.valueOf(rs.getString("frequency"))
        );
    }
}
