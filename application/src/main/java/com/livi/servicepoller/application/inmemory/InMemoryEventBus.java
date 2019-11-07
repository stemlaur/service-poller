package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.EventBus;

import static java.lang.String.format;

public final class InMemoryEventBus implements EventBus {

    @Override
    public void publish(final Object object) {
        System.out.println(format("%s published", object));
    }

}
