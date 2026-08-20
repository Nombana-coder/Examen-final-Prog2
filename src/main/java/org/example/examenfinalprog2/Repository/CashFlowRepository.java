package com.example.repository;

import com.example.model.CashFlow;
import com.example.model.Donation;
import com.example.model.Expense;
import com.example.model.ExpenseFrequency;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public class CashFlowRepository {

    private final JdbcTemplate jdbcTemplate;

    public CashFlowRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<CashFlow> CASH_FLOW_ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        String id = rs.getString("id");
        Instant createdAt = toInstant(rs.getTimestamp("created_at"));
        BigDecimal amount = rs.getBigDecimal("amount");
        String type = rs.getString("type");

        if ("DONATION".equals(type)) {
            String comment = rs.getString("comment");
            return new Donation(id, createdAt, amount, comment);
        } else if ("EXPENSE".equals(type)) {
            String reason = rs.getString("reason");
            String frequencyRaw = rs.getString("frequency");
            ExpenseFrequency frequency = frequencyRaw != null
                    ? ExpenseFrequency.valueOf(frequencyRaw)
                    : ExpenseFrequency.NONE;
            return new Expense(id, createdAt, amount, reason, frequency);
        } else {
            throw new IllegalStateException("Type de CashFlow inconnu en base : " + type);
        }
    };

    private static Instant toInstant(Timestamp timestamp) {
        return timestamp != null ? timestamp.toInstant() : null;
    }

    public List<CashFlow> findByUserId(String userId) {
        String sql = """
                SELECT cf.id            AS id,
                       cf.created_at    AS created_at,
                       cf.amount        AS amount,
                       cf.type          AS type,
                       d.comment        AS comment,
                       e.reason         AS reason,
                       e.frequency      AS frequency
                FROM cash_flow cf
                LEFT JOIN donation d ON d.cash_flow_id = cf.id
                LEFT JOIN expense  e ON e.cash_flow_id = cf.id
                WHERE cf.user_id = ?
                ORDER BY cf.created_at DESC
                """;

        return jdbcTemplate.query(sql, CASH_FLOW_ROW_MAPPER, userId);
    }

    public BigDecimal calculateBalance() {
        String sql = """
                SELECT
                    COALESCE(SUM(CASE WHEN type = 'DONATION' THEN amount ELSE 0 END), 0)
                    -
                    COALESCE(SUM(CASE WHEN type = 'EXPENSE' THEN amount ELSE 0 END), 0)
                    AS balance
                FROM cash_flow
                """;

        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class);
        return balance != null ? balance : BigDecimal.ZERO;
    }
}
