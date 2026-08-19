package com.example.slambook.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseSchemaInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        log.info("Ensuring PostgreSQL table columns are set to TEXT for large photo URLs and strings...");
        
        String[] alterStatements = {
            "ALTER TABLE slam_book ALTER COLUMN profile_photo_url TYPE TEXT;",
            "ALTER TABLE slam_book ALTER COLUMN memory_photo_url TYPE TEXT;",
            "ALTER TABLE slam_book ALTER COLUMN song_url TYPE TEXT;",
            "ALTER TABLE slam_book ALTER COLUMN song_dedication TYPE TEXT;",
            "ALTER TABLE slam_book ALTER COLUMN about_me TYPE TEXT;",
            "ALTER TABLE slam_book ALTER COLUMN memory_text TYPE TEXT;",
            "ALTER TABLE friend ALTER COLUMN memory_photo_url TYPE TEXT;",
            "ALTER TABLE friend ALTER COLUMN song_url TYPE TEXT;",
            "ALTER TABLE friend ALTER COLUMN song_dedication TYPE TEXT;",
            "ALTER TABLE friend ALTER COLUMN message TYPE TEXT;",
            "ALTER TABLE friend ALTER COLUMN memory_text TYPE TEXT;"
        };

        for (String sql : alterStatements) {
            try {
                jdbcTemplate.execute(sql);
            } catch (Exception e) {
                log.debug("Skipping DDL alter query: {} (Error: {})", sql, e.getMessage());
            }
        }
        log.info("PostgreSQL database schema alter check complete.");
    }
}
