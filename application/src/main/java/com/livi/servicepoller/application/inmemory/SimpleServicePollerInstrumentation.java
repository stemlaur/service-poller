package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.ServiceDefinition;
import com.livi.servicepoller.domain.common.ServicePollerInstrumentation;
import org.slf4j.Logger;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

public final class SimpleServicePollerInstrumentation implements ServicePollerInstrumentation {
    private static final Logger LOGGER = getLogger(SimpleServicePollerInstrumentation.class);


    @Override
    public void registeringServiceDefinition(final ServiceDefinition definition) {
        LOGGER.info(format("Registering service definition %s", definition.url()));
    }

    @Override
    public void serviceDefinitionRegistered(final ServiceDefinition definition) {
        LOGGER.info(format("Service definition %s registered", definition.url()));
    }

    @Override
    public void checkingStatuses() {
        LOGGER.debug("Checking all statuses");
    }

    @Override
    public void statusesChecked() {
        LOGGER.debug("All statuses checked");
    }

    @Override
    public void checkingStatusFor(final ServiceDefinition definition) {
        LOGGER.debug(format("Checking status for %s", definition.url()));
    }

    @Override
    public void statusCheckedFor(final ServiceDefinition definition) {
        LOGGER.debug(format("Status checked for %s", definition.url()));
    }
}
