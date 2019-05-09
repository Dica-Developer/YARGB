package com.yarpg.application.configuration.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yarpg.core.usecase.move.MovePlayerModelUseCase;
import com.yarpg.core.usecase.user.UserUseCase;
import com.yarpg.rest.info.InfoRestController;
import com.yarpg.rest.move.MovePlayerModelRestController;

@Configuration
public class RestControllerConfiguration {

    @Bean
    public MovePlayerModelRestController getMovePlayerModelRestController(MovePlayerModelUseCase movePlayerModelUseCase,
            UserUseCase userUseCase) {
        return new MovePlayerModelRestController(movePlayerModelUseCase, userUseCase);
    }

    @Bean
    public InfoRestController getInfoRestController() {
        return new InfoRestController();
    }
}
