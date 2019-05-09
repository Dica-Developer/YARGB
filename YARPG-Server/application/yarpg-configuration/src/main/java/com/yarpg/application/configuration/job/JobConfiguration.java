package com.yarpg.application.configuration.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yarpg.core.usecase.storyteller.StorytellerUseCase;
import com.yarpg.job.ScheduledJob;
import com.yarpg.job.storyteller.Storyteller;

@Configuration
public class JobConfiguration {

    @Bean
    public ScheduledJob createStoryteller(StorytellerUseCase storytellerUseCase) {
        return new Storyteller(storytellerUseCase);
    }
}
