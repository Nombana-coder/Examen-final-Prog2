package org.example.examenfinalprog2.repository;

import org.example.examenfinalprog2.model.Donation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DonationRowMapper implements RowMapper<Donation> {

    @Override
    public Donation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Donation(
                rs.getString("id"),
                rs.getTimestamp("created_at").toInstant(),
                rs.getBigDecimal("amount"),
                rs.getString("user_id"),
                rs.getString("comment")
        );
    }
}
