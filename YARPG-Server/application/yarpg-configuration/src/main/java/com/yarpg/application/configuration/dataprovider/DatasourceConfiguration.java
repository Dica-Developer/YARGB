package com.yarpg.application.configuration.dataprovider;

import java.io.IOException;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DatasourceConfiguration {

    private static final String SCHEMA_INITIALISATION_SCRIPT = "h2-schema.sql";
    private static final String SCHEMA_DATA_SCRIPT = "h2-data.sql";

    @Bean
    public DataSource dataSource() throws IOException {
        // in Memory
        String createDatabase = "runscript from 'classpath:/" + SCHEMA_INITIALISATION_SCRIPT + "'";
        // String addDataToDatabase = "\\;runscript from 'classpath:/" + SCHEMA_DATA_SCRIPT + "'";

        StringBuilder connectionStringBuilder = new StringBuilder();
        connectionStringBuilder.append("jdbc:h2:mem:test;MODE=Oracle;INIT=");
        connectionStringBuilder.append(createDatabase);
        // connectionStringBuilder.append(addDataToDatabase);

        String jdbcUrl = connectionStringBuilder.toString();
        String username = "yarpg";
        String password = "";
        return JdbcConnectionPool.create(jdbcUrl, username, password);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource) {
        return new JdbcTemplate(datasource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
