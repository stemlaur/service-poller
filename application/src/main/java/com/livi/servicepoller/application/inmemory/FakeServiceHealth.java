package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.ServiceHealth;
import com.livi.servicepoller.domain.Status;

import java.util.Random;

public final class FakeServiceHealth implements ServiceHealth {
    @Override
    public Status check(final String url) {
        return pickRandomStatusNotFairly();
    }

    private static Status pickRandomStatusNotFairly() {
        final int randomInt = new Random().nextInt(100);
        if (randomInt < 50) {
            return Status.OK;
        } else {
            return Status.FAIL;
        }
    }
}
