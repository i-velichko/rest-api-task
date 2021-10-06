package com.epam.esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author Ivan Velichko
 * @date 25.09.2021 23:55
 */

@Configuration
@PropertySource("classpath:database.properties")
public class SpringJdbcConfig {

    @Value("${dataSource.driverClassName}")
    private String driverClass;
    @Value("${dataSource.dbUrl}")
    private String dbUrl;
    @Value("${dataSource.userName}")
    private String userName;
    @Value("${dataSource.password}")
    private String password;
    @Value("${dataSource.maxPoolSize}")
    private int maxPoolSize;

    @Bean
    public HikariConfig getHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClass);
        config.setJdbcUrl(dbUrl);
        config.setUsername(userName);
        config.setPassword(password);
        config.setMaximumPoolSize(maxPoolSize);
        return config;
    }


    @Bean
    public DataSource dataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);

    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
