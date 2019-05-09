package com.yarpg.job;

import java.util.concurrent.TimeUnit;

public interface ScheduledJob extends Runnable {

    String getName();

    long getInitialDelay();

    long getPeriod();

    TimeUnit getTimeUnit();

}
