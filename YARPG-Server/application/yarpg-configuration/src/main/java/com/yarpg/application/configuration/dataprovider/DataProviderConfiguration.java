package com.yarpg.application.configuration.dataprovider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yarpg.dataprovider.jdbc.SqlDataProviderImpl;

@Configuration
public class DataProviderConfiguration {

    @Bean
    public SqlDataProviderImpl getSqlDataProviderImpl(JdbcTemplate jdbcTemplate) {
        return new SqlDataProviderImpl(jdbcTemplate);
    }

}
