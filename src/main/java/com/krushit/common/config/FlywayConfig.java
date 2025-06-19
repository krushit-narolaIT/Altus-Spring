package com.krushit.common.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class FlywayConfig {
    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.database}")
    private String database;

    @Value("${db.driver}")
    private String driver;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return Flyway.configure()
//                .dataSource(dbConfig.getDbUrl(), dbConfig.getDbUsername(), dbConfig.getDbPassword())
                .dataSource(url, username, password)
                .schemas(database)
                .locations("classpath:db.migration")
                .load();
    }

}
