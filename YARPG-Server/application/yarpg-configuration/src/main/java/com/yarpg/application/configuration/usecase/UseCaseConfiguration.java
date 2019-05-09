package com.yarpg.application.configuration.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yarpg.core.usecase.MapElementsProvider;
import com.yarpg.core.usecase.PlayerModelProvider;
import com.yarpg.core.usecase.UserProvider;
import com.yarpg.core.usecase.move.MovePlayerModelUseCase;
import com.yarpg.core.usecase.storyteller.StorytellerUseCase;
import com.yarpg.core.usecase.user.UserUseCase;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public MovePlayerModelUseCase getMovePlayerModelUseCase(PlayerModelProvider playerModelProvider,
            MapElementsProvider mapElementsProvider) {
        return new MovePlayerModelUseCase(playerModelProvider, mapElementsProvider);
    }

    @Bean
    public StorytellerUseCase getStorytellerUseCase(MapElementsProvider mapElementsProvider) {
        return new StorytellerUseCase(mapElementsProvider);
    }

    @Bean
    public UserUseCase getUserUseCase(UserProvider userProvider) {
        return new UserUseCase(userProvider);
    }
}
