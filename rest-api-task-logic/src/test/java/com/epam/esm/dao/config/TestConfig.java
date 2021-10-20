package com.epam.esm.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Ivan Velichko
 * @date 10.10.2021 1:14
 */

@Configuration
@ComponentScan("com.epam.esm")
public class TestConfig {

    private static final String SQL_SETUP = "classpath:db_setup.sql";
    private static final String SQL_INIT = "classpath:db_init.sql";

    @Bean
    public EmbeddedDatabase embeddedDatabase() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        return databaseBuilder
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript(SQL_INIT)
                .addScript(SQL_SETUP)
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(EmbeddedDatabase embeddedDatabase) {
        return new JdbcTemplate(embeddedDatabase);
    }

    @Bean
    public PlatformTransactionManager transactionManager(EmbeddedDatabase embeddedDatabase) {
        return new DataSourceTransactionManager(embeddedDatabase);
    }
}