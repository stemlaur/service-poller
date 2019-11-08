package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.common.EventBus;
import org.slf4j.Logger;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

public final class InMemoryEventBus implements EventBus {
    private static final Logger LOGGER = getLogger(InMemoryEventBus.class);

    @Override
    public void publish(final Object object) {
        LOGGER.info(format("%s published", object));
    }

}
