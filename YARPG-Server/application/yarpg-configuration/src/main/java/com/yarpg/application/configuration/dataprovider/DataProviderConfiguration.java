package com.yarpg.application.configuration.dataprovider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yarpg.dataprovider.jdbc.MapElementsProviderImpl;
import com.yarpg.dataprovider.jdbc.PlayerModelProviderImpl;

@Configuration
public class DataProviderConfiguration {

    @Bean
    public PlayerModelProviderImpl getPlayerModelProviderImpl(JdbcTemplate jdbcTemplate) {
        return new PlayerModelProviderImpl(jdbcTemplate);
    }

    @Bean
    public MapElementsProviderImpl getMapElementsProviderImpl(JdbcTemplate jdbcTemplate) {
        return new MapElementsProviderImpl(jdbcTemplate);
    }

}
