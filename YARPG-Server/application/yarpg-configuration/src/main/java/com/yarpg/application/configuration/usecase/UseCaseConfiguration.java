package com.yarpg.application.configuration.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yarpg.core.usecase.move.MapElementsProvider;
import com.yarpg.core.usecase.move.MovePlayerModelUseCase;
import com.yarpg.core.usecase.move.PlayerModelProvider;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public MovePlayerModelUseCase getMovePlayerModelUseCase(PlayerModelProvider playerModelProvider,
            MapElementsProvider mapElementsProvider) {
        return new MovePlayerModelUseCase(playerModelProvider, mapElementsProvider);
    }
}
