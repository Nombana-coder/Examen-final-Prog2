package org.example.examenfinalprog2.repository;

import org.example.examenfinalprog2.model.Donation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DonationRepository {

    private final JdbcTemplate jdbcTemplate;
    private final DonationRowMapper rowMapper = new DonationRowMapper();

    public DonationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Donation> findAll() {
        return jdbcTemplate.query("SELECT * FROM donation", rowMapper);
    }

    public List<Donation> findByUserId(String userId) {
        return jdbcTemplate.query(
                "SELECT * FROM donation WHERE user_id = ?",
                rowMapper,
                userId
        );
    }
}
