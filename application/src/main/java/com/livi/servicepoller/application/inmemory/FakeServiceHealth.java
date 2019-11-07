package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.ServiceHealth;
import com.livi.servicepoller.domain.Status;

import java.util.Random;

public final class FakeServiceHealth implements ServiceHealth {
    @Override
    public Status check(final String urlName) {
        return pickRandomStatusNotFairly();
    }

    private static Status pickRandomStatusNotFairly() {
        final int randomInt = new Random().nextInt(100);
        System.out.println("random number = " + randomInt);
        if (randomInt < 80) {
            return Status.OK;
        } else {
            return Status.FAIL;
        }
    }
}
