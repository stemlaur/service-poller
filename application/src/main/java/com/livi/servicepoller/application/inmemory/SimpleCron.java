package com.livi.servicepoller.application.inmemory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class SimpleCron {
    private final ScheduledExecutorService scheduler;

    public SimpleCron(final Runnable command) {
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.scheduler.scheduleAtFixedRate(command, 1, 5, TimeUnit.SECONDS);
    }

    public void shutdown() {
        this.scheduler.shutdown();
    }
}
