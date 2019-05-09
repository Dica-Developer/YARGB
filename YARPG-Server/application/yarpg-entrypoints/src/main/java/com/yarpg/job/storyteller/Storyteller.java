package com.yarpg.job.storyteller;

import java.util.concurrent.TimeUnit;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.usecase.storyteller.StorytellerUseCase;
import com.yarpg.job.ScheduledJob;

public class Storyteller implements ScheduledJob {

    private StorytellerUseCase _storytellerUseCase;

    public Storyteller(StorytellerUseCase storytellerUseCase) {
        _storytellerUseCase = storytellerUseCase;
    }

    @Override
    public void run() {
        System.out.println("Storyteller running");
        GeoRef pStart = new GeoRef(51.52, 11.8);
        GeoRef pEnd = new GeoRef(51.3, 12.1);
        _storytellerUseCase.randomizeEcounterBetween(pStart, pEnd);
    }

    @Override
    public String getName() {
        return this.getClass()
                .getName();
    }

    @Override
    public long getInitialDelay() {
        return 1;
    }

    @Override
    public long getPeriod() {
        return 5;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.MINUTES;
    }

}
